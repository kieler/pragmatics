package de.cau.cs.kieler.ksbase.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.common.editor.contentassist.antlr.internal.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class InternalFeatureDefinitionLexer extends Lexer {
    public static final int RULE_ID=5;
    public static final int RULE_ANY_OTHER=10;
    public static final int Tokens=24;
    public static final int EOF=-1;
    public static final int RULE_SL_COMMENT=8;
    public static final int T23=23;
    public static final int T22=22;
    public static final int T21=21;
    public static final int T20=20;
    public static final int RULE_ML_COMMENT=7;
    public static final int RULE_STRING=4;
    public static final int RULE_INT=6;
    public static final int T11=11;
    public static final int T12=12;
    public static final int T13=13;
    public static final int T14=14;
    public static final int RULE_WS=9;
    public static final int T15=15;
    public static final int T16=16;
    public static final int T17=17;
    public static final int T18=18;
    public static final int T19=19;
    public InternalFeatureDefinitionLexer() {;} 
    public InternalFeatureDefinitionLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g"; }

    // $ANTLR start T11
    public final void mT11() throws RecognitionException {
        try {
            int _type = T11;
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:10:5: ( 'ModelPackage = ' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:10:7: 'ModelPackage = '
            {
            match("ModelPackage = "); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T11

    // $ANTLR start T12
    public final void mT12() throws RecognitionException {
        try {
            int _type = T12;
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:11:5: ( ';' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:11:7: ';'
            {
            match(';'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T12

    // $ANTLR start T13
    public final void mT13() throws RecognitionException {
        try {
            int _type = T13;
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:12:5: ( 'FeatureMenuTitle = ' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:12:7: 'FeatureMenuTitle = '
            {
            match("FeatureMenuTitle = "); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T13

    // $ANTLR start T14
    public final void mT14() throws RecognitionException {
        try {
            int _type = T14;
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:13:5: ( 'ModelPackagePath = ' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:13:7: 'ModelPackagePath = '
            {
            match("ModelPackagePath = "); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T14

    // $ANTLR start T15
    public final void mT15() throws RecognitionException {
        try {
            int _type = T15;
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:14:5: ( 'DiagramPackage = ' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:14:7: 'DiagramPackage = '
            {
            match("DiagramPackage = "); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T15

    // $ANTLR start T16
    public final void mT16() throws RecognitionException {
        try {
            int _type = T16;
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:15:5: ( 'TransformationFile = ' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:15:7: 'TransformationFile = '
            {
            match("TransformationFile = "); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T16

    // $ANTLR start T17
    public final void mT17() throws RecognitionException {
        try {
            int _type = T17;
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:16:5: ( 'Feature' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:16:7: 'Feature'
            {
            match("Feature"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T17

    // $ANTLR start T18
    public final void mT18() throws RecognitionException {
        try {
            int _type = T18;
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:17:5: ( '{' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:17:7: '{'
            {
            match('{'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T18

    // $ANTLR start T19
    public final void mT19() throws RecognitionException {
        try {
            int _type = T19;
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:18:5: ( 'MethodName = ' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:18:7: 'MethodName = '
            {
            match("MethodName = "); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T19

    // $ANTLR start T20
    public final void mT20() throws RecognitionException {
        try {
            int _type = T20;
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:19:5: ( 'MenuEntry = ' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:19:7: 'MenuEntry = '
            {
            match("MenuEntry = "); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T20

    // $ANTLR start T21
    public final void mT21() throws RecognitionException {
        try {
            int _type = T21;
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:20:5: ( '}' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:20:7: '}'
            {
            match('}'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T21

    // $ANTLR start T22
    public final void mT22() throws RecognitionException {
        try {
            int _type = T22;
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:21:5: ( 'File = ' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:21:7: 'File = '
            {
            match("File = "); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T22

    // $ANTLR start T23
    public final void mT23() throws RecognitionException {
        try {
            int _type = T23;
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:22:5: ( 'Parameter = ' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:22:7: 'Parameter = '
            {
            match("Parameter = "); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T23

    // $ANTLR start RULE_ID
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1059:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1059:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1059:11: ( '^' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='^') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1059:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1059:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ID

    // $ANTLR start RULE_INT
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1061:10: ( ( '0' .. '9' )+ )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1061:12: ( '0' .. '9' )+
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1061:12: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1061:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_INT

    // $ANTLR start RULE_STRING
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1063:13: ( ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1063:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1063:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='\"') ) {
                alt6=1;
            }
            else if ( (LA6_0=='\'') ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1063:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1063:16: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1063:20: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop4:
                    do {
                        int alt4=3;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0=='\\') ) {
                            alt4=1;
                        }
                        else if ( ((LA4_0>='\u0000' && LA4_0<='!')||(LA4_0>='#' && LA4_0<='[')||(LA4_0>=']' && LA4_0<='\uFFFE')) ) {
                            alt4=2;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1063:21: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1063:62: ~ ( ( '\\\\' | '\"' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1063:82: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1063:87: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop5:
                    do {
                        int alt5=3;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0=='\\') ) {
                            alt5=1;
                        }
                        else if ( ((LA5_0>='\u0000' && LA5_0<='&')||(LA5_0>='(' && LA5_0<='[')||(LA5_0>=']' && LA5_0<='\uFFFE')) ) {
                            alt5=2;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1063:88: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1063:129: ~ ( ( '\\\\' | '\\'' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_STRING

    // $ANTLR start RULE_ML_COMMENT
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1065:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1065:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1065:24: ( options {greedy=false; } : . )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='*') ) {
                    int LA7_1 = input.LA(2);

                    if ( (LA7_1=='/') ) {
                        alt7=2;
                    }
                    else if ( ((LA7_1>='\u0000' && LA7_1<='.')||(LA7_1>='0' && LA7_1<='\uFFFE')) ) {
                        alt7=1;
                    }


                }
                else if ( ((LA7_0>='\u0000' && LA7_0<=')')||(LA7_0>='+' && LA7_0<='\uFFFE')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1065:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            match("*/"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ML_COMMENT

    // $ANTLR start RULE_SL_COMMENT
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1067:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1067:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1067:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='\u0000' && LA8_0<='\t')||(LA8_0>='\u000B' && LA8_0<='\f')||(LA8_0>='\u000E' && LA8_0<='\uFFFE')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1067:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1067:40: ( ( '\\r' )? '\\n' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='\n'||LA10_0=='\r') ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1067:41: ( '\\r' )? '\\n'
                    {
                    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1067:41: ( '\\r' )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0=='\r') ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1067:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_SL_COMMENT

    // $ANTLR start RULE_WS
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1069:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1069:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1069:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>='\t' && LA11_0<='\n')||LA11_0=='\r'||LA11_0==' ') ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
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

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_WS

    // $ANTLR start RULE_ANY_OTHER
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1071:16: ( . )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1071:18: .
            {
            matchAny(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ANY_OTHER

    public void mTokens() throws RecognitionException {
        // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1:8: ( T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt12=20;
        int LA12_0 = input.LA(1);

        if ( (LA12_0=='M') ) {
            switch ( input.LA(2) ) {
            case 'e':
                {
                switch ( input.LA(3) ) {
                case 't':
                    {
                    int LA12_33 = input.LA(4);

                    if ( (LA12_33=='h') ) {
                        int LA12_41 = input.LA(5);

                        if ( (LA12_41=='o') ) {
                            int LA12_49 = input.LA(6);

                            if ( (LA12_49=='d') ) {
                                int LA12_57 = input.LA(7);

                                if ( (LA12_57=='N') ) {
                                    int LA12_64 = input.LA(8);

                                    if ( (LA12_64=='a') ) {
                                        int LA12_71 = input.LA(9);

                                        if ( (LA12_71=='m') ) {
                                            int LA12_79 = input.LA(10);

                                            if ( (LA12_79=='e') ) {
                                                int LA12_86 = input.LA(11);

                                                if ( (LA12_86==' ') ) {
                                                    alt12=9;
                                                }
                                                else {
                                                    alt12=14;}
                                            }
                                            else {
                                                alt12=14;}
                                        }
                                        else {
                                            alt12=14;}
                                    }
                                    else {
                                        alt12=14;}
                                }
                                else {
                                    alt12=14;}
                            }
                            else {
                                alt12=14;}
                        }
                        else {
                            alt12=14;}
                    }
                    else {
                        alt12=14;}
                    }
                    break;
                case 'n':
                    {
                    int LA12_34 = input.LA(4);

                    if ( (LA12_34=='u') ) {
                        int LA12_42 = input.LA(5);

                        if ( (LA12_42=='E') ) {
                            int LA12_50 = input.LA(6);

                            if ( (LA12_50=='n') ) {
                                int LA12_58 = input.LA(7);

                                if ( (LA12_58=='t') ) {
                                    int LA12_65 = input.LA(8);

                                    if ( (LA12_65=='r') ) {
                                        int LA12_72 = input.LA(9);

                                        if ( (LA12_72=='y') ) {
                                            int LA12_80 = input.LA(10);

                                            if ( (LA12_80==' ') ) {
                                                alt12=10;
                                            }
                                            else {
                                                alt12=14;}
                                        }
                                        else {
                                            alt12=14;}
                                    }
                                    else {
                                        alt12=14;}
                                }
                                else {
                                    alt12=14;}
                            }
                            else {
                                alt12=14;}
                        }
                        else {
                            alt12=14;}
                    }
                    else {
                        alt12=14;}
                    }
                    break;
                default:
                    alt12=14;}

                }
                break;
            case 'o':
                {
                int LA12_18 = input.LA(3);

                if ( (LA12_18=='d') ) {
                    int LA12_35 = input.LA(4);

                    if ( (LA12_35=='e') ) {
                        int LA12_43 = input.LA(5);

                        if ( (LA12_43=='l') ) {
                            int LA12_51 = input.LA(6);

                            if ( (LA12_51=='P') ) {
                                int LA12_59 = input.LA(7);

                                if ( (LA12_59=='a') ) {
                                    int LA12_66 = input.LA(8);

                                    if ( (LA12_66=='c') ) {
                                        int LA12_73 = input.LA(9);

                                        if ( (LA12_73=='k') ) {
                                            int LA12_81 = input.LA(10);

                                            if ( (LA12_81=='a') ) {
                                                int LA12_88 = input.LA(11);

                                                if ( (LA12_88=='g') ) {
                                                    int LA12_94 = input.LA(12);

                                                    if ( (LA12_94=='e') ) {
                                                        switch ( input.LA(13) ) {
                                                        case 'P':
                                                            {
                                                            int LA12_102 = input.LA(14);

                                                            if ( (LA12_102=='a') ) {
                                                                int LA12_107 = input.LA(15);

                                                                if ( (LA12_107=='t') ) {
                                                                    int LA12_111 = input.LA(16);

                                                                    if ( (LA12_111=='h') ) {
                                                                        int LA12_115 = input.LA(17);

                                                                        if ( (LA12_115==' ') ) {
                                                                            alt12=4;
                                                                        }
                                                                        else {
                                                                            alt12=14;}
                                                                    }
                                                                    else {
                                                                        alt12=14;}
                                                                }
                                                                else {
                                                                    alt12=14;}
                                                            }
                                                            else {
                                                                alt12=14;}
                                                            }
                                                            break;
                                                        case ' ':
                                                            {
                                                            alt12=1;
                                                            }
                                                            break;
                                                        default:
                                                            alt12=14;}

                                                    }
                                                    else {
                                                        alt12=14;}
                                                }
                                                else {
                                                    alt12=14;}
                                            }
                                            else {
                                                alt12=14;}
                                        }
                                        else {
                                            alt12=14;}
                                    }
                                    else {
                                        alt12=14;}
                                }
                                else {
                                    alt12=14;}
                            }
                            else {
                                alt12=14;}
                        }
                        else {
                            alt12=14;}
                    }
                    else {
                        alt12=14;}
                }
                else {
                    alt12=14;}
                }
                break;
            default:
                alt12=14;}

        }
        else if ( (LA12_0==';') ) {
            alt12=2;
        }
        else if ( (LA12_0=='F') ) {
            switch ( input.LA(2) ) {
            case 'i':
                {
                int LA12_21 = input.LA(3);

                if ( (LA12_21=='l') ) {
                    int LA12_36 = input.LA(4);

                    if ( (LA12_36=='e') ) {
                        int LA12_44 = input.LA(5);

                        if ( (LA12_44==' ') ) {
                            alt12=12;
                        }
                        else {
                            alt12=14;}
                    }
                    else {
                        alt12=14;}
                }
                else {
                    alt12=14;}
                }
                break;
            case 'e':
                {
                int LA12_22 = input.LA(3);

                if ( (LA12_22=='a') ) {
                    int LA12_37 = input.LA(4);

                    if ( (LA12_37=='t') ) {
                        int LA12_45 = input.LA(5);

                        if ( (LA12_45=='u') ) {
                            int LA12_53 = input.LA(6);

                            if ( (LA12_53=='r') ) {
                                int LA12_60 = input.LA(7);

                                if ( (LA12_60=='e') ) {
                                    switch ( input.LA(8) ) {
                                    case 'M':
                                        {
                                        int LA12_74 = input.LA(9);

                                        if ( (LA12_74=='e') ) {
                                            int LA12_82 = input.LA(10);

                                            if ( (LA12_82=='n') ) {
                                                int LA12_89 = input.LA(11);

                                                if ( (LA12_89=='u') ) {
                                                    int LA12_95 = input.LA(12);

                                                    if ( (LA12_95=='T') ) {
                                                        int LA12_99 = input.LA(13);

                                                        if ( (LA12_99=='i') ) {
                                                            int LA12_104 = input.LA(14);

                                                            if ( (LA12_104=='t') ) {
                                                                int LA12_108 = input.LA(15);

                                                                if ( (LA12_108=='l') ) {
                                                                    int LA12_112 = input.LA(16);

                                                                    if ( (LA12_112=='e') ) {
                                                                        int LA12_116 = input.LA(17);

                                                                        if ( (LA12_116==' ') ) {
                                                                            alt12=3;
                                                                        }
                                                                        else {
                                                                            alt12=14;}
                                                                    }
                                                                    else {
                                                                        alt12=14;}
                                                                }
                                                                else {
                                                                    alt12=14;}
                                                            }
                                                            else {
                                                                alt12=14;}
                                                        }
                                                        else {
                                                            alt12=14;}
                                                    }
                                                    else {
                                                        alt12=14;}
                                                }
                                                else {
                                                    alt12=14;}
                                            }
                                            else {
                                                alt12=14;}
                                        }
                                        else {
                                            alt12=14;}
                                        }
                                        break;
                                    case '0':
                                    case '1':
                                    case '2':
                                    case '3':
                                    case '4':
                                    case '5':
                                    case '6':
                                    case '7':
                                    case '8':
                                    case '9':
                                    case 'A':
                                    case 'B':
                                    case 'C':
                                    case 'D':
                                    case 'E':
                                    case 'F':
                                    case 'G':
                                    case 'H':
                                    case 'I':
                                    case 'J':
                                    case 'K':
                                    case 'L':
                                    case 'N':
                                    case 'O':
                                    case 'P':
                                    case 'Q':
                                    case 'R':
                                    case 'S':
                                    case 'T':
                                    case 'U':
                                    case 'V':
                                    case 'W':
                                    case 'X':
                                    case 'Y':
                                    case 'Z':
                                    case '_':
                                    case 'a':
                                    case 'b':
                                    case 'c':
                                    case 'd':
                                    case 'e':
                                    case 'f':
                                    case 'g':
                                    case 'h':
                                    case 'i':
                                    case 'j':
                                    case 'k':
                                    case 'l':
                                    case 'm':
                                    case 'n':
                                    case 'o':
                                    case 'p':
                                    case 'q':
                                    case 'r':
                                    case 's':
                                    case 't':
                                    case 'u':
                                    case 'v':
                                    case 'w':
                                    case 'x':
                                    case 'y':
                                    case 'z':
                                        {
                                        alt12=14;
                                        }
                                        break;
                                    default:
                                        alt12=7;}

                                }
                                else {
                                    alt12=14;}
                            }
                            else {
                                alt12=14;}
                        }
                        else {
                            alt12=14;}
                    }
                    else {
                        alt12=14;}
                }
                else {
                    alt12=14;}
                }
                break;
            default:
                alt12=14;}

        }
        else if ( (LA12_0=='D') ) {
            int LA12_4 = input.LA(2);

            if ( (LA12_4=='i') ) {
                int LA12_23 = input.LA(3);

                if ( (LA12_23=='a') ) {
                    int LA12_38 = input.LA(4);

                    if ( (LA12_38=='g') ) {
                        int LA12_46 = input.LA(5);

                        if ( (LA12_46=='r') ) {
                            int LA12_54 = input.LA(6);

                            if ( (LA12_54=='a') ) {
                                int LA12_61 = input.LA(7);

                                if ( (LA12_61=='m') ) {
                                    int LA12_68 = input.LA(8);

                                    if ( (LA12_68=='P') ) {
                                        int LA12_76 = input.LA(9);

                                        if ( (LA12_76=='a') ) {
                                            int LA12_83 = input.LA(10);

                                            if ( (LA12_83=='c') ) {
                                                int LA12_90 = input.LA(11);

                                                if ( (LA12_90=='k') ) {
                                                    int LA12_96 = input.LA(12);

                                                    if ( (LA12_96=='a') ) {
                                                        int LA12_100 = input.LA(13);

                                                        if ( (LA12_100=='g') ) {
                                                            int LA12_105 = input.LA(14);

                                                            if ( (LA12_105=='e') ) {
                                                                int LA12_109 = input.LA(15);

                                                                if ( (LA12_109==' ') ) {
                                                                    alt12=5;
                                                                }
                                                                else {
                                                                    alt12=14;}
                                                            }
                                                            else {
                                                                alt12=14;}
                                                        }
                                                        else {
                                                            alt12=14;}
                                                    }
                                                    else {
                                                        alt12=14;}
                                                }
                                                else {
                                                    alt12=14;}
                                            }
                                            else {
                                                alt12=14;}
                                        }
                                        else {
                                            alt12=14;}
                                    }
                                    else {
                                        alt12=14;}
                                }
                                else {
                                    alt12=14;}
                            }
                            else {
                                alt12=14;}
                        }
                        else {
                            alt12=14;}
                    }
                    else {
                        alt12=14;}
                }
                else {
                    alt12=14;}
            }
            else {
                alt12=14;}
        }
        else if ( (LA12_0=='T') ) {
            int LA12_5 = input.LA(2);

            if ( (LA12_5=='r') ) {
                int LA12_24 = input.LA(3);

                if ( (LA12_24=='a') ) {
                    int LA12_39 = input.LA(4);

                    if ( (LA12_39=='n') ) {
                        int LA12_47 = input.LA(5);

                        if ( (LA12_47=='s') ) {
                            int LA12_55 = input.LA(6);

                            if ( (LA12_55=='f') ) {
                                int LA12_62 = input.LA(7);

                                if ( (LA12_62=='o') ) {
                                    int LA12_69 = input.LA(8);

                                    if ( (LA12_69=='r') ) {
                                        int LA12_77 = input.LA(9);

                                        if ( (LA12_77=='m') ) {
                                            int LA12_84 = input.LA(10);

                                            if ( (LA12_84=='a') ) {
                                                int LA12_91 = input.LA(11);

                                                if ( (LA12_91=='t') ) {
                                                    int LA12_97 = input.LA(12);

                                                    if ( (LA12_97=='i') ) {
                                                        int LA12_101 = input.LA(13);

                                                        if ( (LA12_101=='o') ) {
                                                            int LA12_106 = input.LA(14);

                                                            if ( (LA12_106=='n') ) {
                                                                int LA12_110 = input.LA(15);

                                                                if ( (LA12_110=='F') ) {
                                                                    int LA12_114 = input.LA(16);

                                                                    if ( (LA12_114=='i') ) {
                                                                        int LA12_117 = input.LA(17);

                                                                        if ( (LA12_117=='l') ) {
                                                                            int LA12_120 = input.LA(18);

                                                                            if ( (LA12_120=='e') ) {
                                                                                int LA12_121 = input.LA(19);

                                                                                if ( (LA12_121==' ') ) {
                                                                                    alt12=6;
                                                                                }
                                                                                else {
                                                                                    alt12=14;}
                                                                            }
                                                                            else {
                                                                                alt12=14;}
                                                                        }
                                                                        else {
                                                                            alt12=14;}
                                                                    }
                                                                    else {
                                                                        alt12=14;}
                                                                }
                                                                else {
                                                                    alt12=14;}
                                                            }
                                                            else {
                                                                alt12=14;}
                                                        }
                                                        else {
                                                            alt12=14;}
                                                    }
                                                    else {
                                                        alt12=14;}
                                                }
                                                else {
                                                    alt12=14;}
                                            }
                                            else {
                                                alt12=14;}
                                        }
                                        else {
                                            alt12=14;}
                                    }
                                    else {
                                        alt12=14;}
                                }
                                else {
                                    alt12=14;}
                            }
                            else {
                                alt12=14;}
                        }
                        else {
                            alt12=14;}
                    }
                    else {
                        alt12=14;}
                }
                else {
                    alt12=14;}
            }
            else {
                alt12=14;}
        }
        else if ( (LA12_0=='{') ) {
            alt12=8;
        }
        else if ( (LA12_0=='}') ) {
            alt12=11;
        }
        else if ( (LA12_0=='P') ) {
            int LA12_8 = input.LA(2);

            if ( (LA12_8=='a') ) {
                int LA12_27 = input.LA(3);

                if ( (LA12_27=='r') ) {
                    int LA12_40 = input.LA(4);

                    if ( (LA12_40=='a') ) {
                        int LA12_48 = input.LA(5);

                        if ( (LA12_48=='m') ) {
                            int LA12_56 = input.LA(6);

                            if ( (LA12_56=='e') ) {
                                int LA12_63 = input.LA(7);

                                if ( (LA12_63=='t') ) {
                                    int LA12_70 = input.LA(8);

                                    if ( (LA12_70=='e') ) {
                                        int LA12_78 = input.LA(9);

                                        if ( (LA12_78=='r') ) {
                                            int LA12_85 = input.LA(10);

                                            if ( (LA12_85==' ') ) {
                                                alt12=13;
                                            }
                                            else {
                                                alt12=14;}
                                        }
                                        else {
                                            alt12=14;}
                                    }
                                    else {
                                        alt12=14;}
                                }
                                else {
                                    alt12=14;}
                            }
                            else {
                                alt12=14;}
                        }
                        else {
                            alt12=14;}
                    }
                    else {
                        alt12=14;}
                }
                else {
                    alt12=14;}
            }
            else {
                alt12=14;}
        }
        else if ( (LA12_0=='^') ) {
            int LA12_9 = input.LA(2);

            if ( ((LA12_9>='A' && LA12_9<='Z')||LA12_9=='_'||(LA12_9>='a' && LA12_9<='z')) ) {
                alt12=14;
            }
            else {
                alt12=20;}
        }
        else if ( ((LA12_0>='A' && LA12_0<='C')||LA12_0=='E'||(LA12_0>='G' && LA12_0<='L')||(LA12_0>='N' && LA12_0<='O')||(LA12_0>='Q' && LA12_0<='S')||(LA12_0>='U' && LA12_0<='Z')||LA12_0=='_'||(LA12_0>='a' && LA12_0<='z')) ) {
            alt12=14;
        }
        else if ( ((LA12_0>='0' && LA12_0<='9')) ) {
            alt12=15;
        }
        else if ( (LA12_0=='\"') ) {
            int LA12_12 = input.LA(2);

            if ( ((LA12_12>='\u0000' && LA12_12<='\uFFFE')) ) {
                alt12=16;
            }
            else {
                alt12=20;}
        }
        else if ( (LA12_0=='\'') ) {
            int LA12_13 = input.LA(2);

            if ( ((LA12_13>='\u0000' && LA12_13<='\uFFFE')) ) {
                alt12=16;
            }
            else {
                alt12=20;}
        }
        else if ( (LA12_0=='/') ) {
            switch ( input.LA(2) ) {
            case '*':
                {
                alt12=17;
                }
                break;
            case '/':
                {
                alt12=18;
                }
                break;
            default:
                alt12=20;}

        }
        else if ( ((LA12_0>='\t' && LA12_0<='\n')||LA12_0=='\r'||LA12_0==' ') ) {
            alt12=19;
        }
        else if ( ((LA12_0>='\u0000' && LA12_0<='\b')||(LA12_0>='\u000B' && LA12_0<='\f')||(LA12_0>='\u000E' && LA12_0<='\u001F')||LA12_0=='!'||(LA12_0>='#' && LA12_0<='&')||(LA12_0>='(' && LA12_0<='.')||LA12_0==':'||(LA12_0>='<' && LA12_0<='@')||(LA12_0>='[' && LA12_0<=']')||LA12_0=='`'||LA12_0=='|'||(LA12_0>='~' && LA12_0<='\uFFFE')) ) {
            alt12=20;
        }
        else {
            NoViableAltException nvae =
                new NoViableAltException("1:1: Tokens : ( T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );", 12, 0, input);

            throw nvae;
        }
        switch (alt12) {
            case 1 :
                // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1:10: T11
                {
                mT11(); 

                }
                break;
            case 2 :
                // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1:14: T12
                {
                mT12(); 

                }
                break;
            case 3 :
                // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1:18: T13
                {
                mT13(); 

                }
                break;
            case 4 :
                // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1:22: T14
                {
                mT14(); 

                }
                break;
            case 5 :
                // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1:26: T15
                {
                mT15(); 

                }
                break;
            case 6 :
                // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1:30: T16
                {
                mT16(); 

                }
                break;
            case 7 :
                // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1:34: T17
                {
                mT17(); 

                }
                break;
            case 8 :
                // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1:38: T18
                {
                mT18(); 

                }
                break;
            case 9 :
                // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1:42: T19
                {
                mT19(); 

                }
                break;
            case 10 :
                // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1:46: T20
                {
                mT20(); 

                }
                break;
            case 11 :
                // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1:50: T21
                {
                mT21(); 

                }
                break;
            case 12 :
                // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1:54: T22
                {
                mT22(); 

                }
                break;
            case 13 :
                // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1:58: T23
                {
                mT23(); 

                }
                break;
            case 14 :
                // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1:62: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 15 :
                // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1:70: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 16 :
                // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1:79: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 17 :
                // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1:91: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 18 :
                // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1:107: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 19 :
                // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1:123: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 20 :
                // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1:131: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


 

}