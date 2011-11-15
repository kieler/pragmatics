package de.cau.cs.kieler.kiml.formats.gml.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalGMLLexer extends Lexer {
    public static final int RULE_ID=4;
    public static final int RULE_STRING=7;
    public static final int T__11=11;
    public static final int T__10=10;
    public static final int RULE_GML_FLOAT=6;
    public static final int RULE_PREC_LINE=8;
    public static final int RULE_WS=9;
    public static final int RULE_GML_INT=5;
    public static final int EOF=-1;

    // delegates
    // delegators

    public InternalGMLLexer() {;} 
    public InternalGMLLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalGMLLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g"; }

    // $ANTLR start "T__10"
    public final void mT__10() throws RecognitionException {
        try {
            int _type = T__10;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:11:7: ( '[' )
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:11:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__10"

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:12:7: ( ']' )
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:12:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__11"

    // $ANTLR start "RULE_GML_INT"
    public final void mRULE_GML_INT() throws RecognitionException {
        try {
            int _type = RULE_GML_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:233:14: ( ( '-' | '+' )? ( '0' .. '9' )+ )
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:233:16: ( '-' | '+' )? ( '0' .. '9' )+
            {
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:233:16: ( '-' | '+' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='+'||LA1_0=='-') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:233:27: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:233:28: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_GML_INT"

    // $ANTLR start "RULE_GML_FLOAT"
    public final void mRULE_GML_FLOAT() throws RecognitionException {
        try {
            int _type = RULE_GML_FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:235:16: ( ( '-' | '+' )? ( '0' .. '9' )* '.' ( '0' .. '9' )* ( ( 'E' | 'e' ) ( '-' | '+' )? ( '0' .. '9' )+ )? )
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:235:18: ( '-' | '+' )? ( '0' .. '9' )* '.' ( '0' .. '9' )* ( ( 'E' | 'e' ) ( '-' | '+' )? ( '0' .. '9' )+ )?
            {
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:235:18: ( '-' | '+' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='+'||LA3_0=='-') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:235:29: ( '0' .. '9' )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:235:30: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            match('.'); 
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:235:45: ( '0' .. '9' )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:235:46: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:235:57: ( ( 'E' | 'e' ) ( '-' | '+' )? ( '0' .. '9' )+ )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0=='E'||LA8_0=='e') ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:235:58: ( 'E' | 'e' ) ( '-' | '+' )? ( '0' .. '9' )+
                    {
                    if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:235:68: ( '-' | '+' )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0=='+'||LA6_0=='-') ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:
                            {
                            if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:235:79: ( '0' .. '9' )+
                    int cnt7=0;
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( ((LA7_0>='0' && LA7_0<='9')) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:235:80: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

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


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_GML_FLOAT"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:237:13: ( '\"' ( '&' ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '#' )+ ';' | ~ ( ( '&' | '\"' ) ) )* '\"' )
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:237:15: '\"' ( '&' ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '#' )+ ';' | ~ ( ( '&' | '\"' ) ) )* '\"'
            {
            match('\"'); 
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:237:19: ( '&' ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '#' )+ ';' | ~ ( ( '&' | '\"' ) ) )*
            loop10:
            do {
                int alt10=3;
                int LA10_0 = input.LA(1);

                if ( (LA10_0=='&') ) {
                    alt10=1;
                }
                else if ( ((LA10_0>='\u0000' && LA10_0<='!')||(LA10_0>='#' && LA10_0<='%')||(LA10_0>='\'' && LA10_0<='\uFFFF')) ) {
                    alt10=2;
                }


                switch (alt10) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:237:20: '&' ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '#' )+ ';'
            	    {
            	    match('&'); 
            	    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:237:24: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '#' )+
            	    int cnt9=0;
            	    loop9:
            	    do {
            	        int alt9=2;
            	        int LA9_0 = input.LA(1);

            	        if ( (LA9_0=='#'||(LA9_0>='0' && LA9_0<='9')||(LA9_0>='A' && LA9_0<='Z')||(LA9_0>='a' && LA9_0<='z')) ) {
            	            alt9=1;
            	        }


            	        switch (alt9) {
            	    	case 1 :
            	    	    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:
            	    	    {
            	    	    if ( input.LA(1)=='#'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	    	        input.consume();

            	    	    }
            	    	    else {
            	    	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	    	        recover(mse);
            	    	        throw mse;}


            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt9 >= 1 ) break loop9;
            	                EarlyExitException eee =
            	                    new EarlyExitException(9, input);
            	                throw eee;
            	        }
            	        cnt9++;
            	    } while (true);

            	    match(';'); 

            	    }
            	    break;
            	case 2 :
            	    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:237:62: ~ ( ( '&' | '\"' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='%')||(input.LA(1)>='\'' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:239:9: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:239:11: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:239:35: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>='0' && LA11_0<='9')||(LA11_0>='A' && LA11_0<='Z')||LA11_0=='_'||(LA11_0>='a' && LA11_0<='z')) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_PREC_LINE"
    public final void mRULE_PREC_LINE() throws RecognitionException {
        try {
            int _type = RULE_PREC_LINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:241:16: ( '#' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:241:18: '#' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match('#'); 
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:241:22: (~ ( ( '\\n' | '\\r' ) ) )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>='\u0000' && LA12_0<='\t')||(LA12_0>='\u000B' && LA12_0<='\f')||(LA12_0>='\u000E' && LA12_0<='\uFFFF')) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:241:22: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:241:38: ( ( '\\r' )? '\\n' )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0=='\n'||LA14_0=='\r') ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:241:39: ( '\\r' )? '\\n'
                    {
                    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:241:39: ( '\\r' )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0=='\r') ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:241:39: '\\r'
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

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_PREC_LINE"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:243:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:243:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:243:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>='\t' && LA15_0<='\n')||LA15_0=='\r'||LA15_0==' ') ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt15 >= 1 ) break loop15;
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    public void mTokens() throws RecognitionException {
        // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:1:8: ( T__10 | T__11 | RULE_GML_INT | RULE_GML_FLOAT | RULE_STRING | RULE_ID | RULE_PREC_LINE | RULE_WS )
        int alt16=8;
        alt16 = dfa16.predict(input);
        switch (alt16) {
            case 1 :
                // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:1:10: T__10
                {
                mT__10(); 

                }
                break;
            case 2 :
                // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:1:16: T__11
                {
                mT__11(); 

                }
                break;
            case 3 :
                // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:1:22: RULE_GML_INT
                {
                mRULE_GML_INT(); 

                }
                break;
            case 4 :
                // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:1:35: RULE_GML_FLOAT
                {
                mRULE_GML_FLOAT(); 

                }
                break;
            case 5 :
                // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:1:50: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 6 :
                // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:1:62: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 7 :
                // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:1:70: RULE_PREC_LINE
                {
                mRULE_PREC_LINE(); 

                }
                break;
            case 8 :
                // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:1:85: RULE_WS
                {
                mRULE_WS(); 

                }
                break;

        }

    }


    protected DFA16 dfa16 = new DFA16(this);
    static final String DFA16_eotS =
        "\4\uffff\1\12\6\uffff";
    static final String DFA16_eofS =
        "\13\uffff";
    static final String DFA16_minS =
        "\1\11\2\uffff\2\56\6\uffff";
    static final String DFA16_maxS =
        "\1\172\2\uffff\2\71\6\uffff";
    static final String DFA16_acceptS =
        "\1\uffff\1\1\1\2\2\uffff\1\4\1\5\1\6\1\7\1\10\1\3";
    static final String DFA16_specialS =
        "\13\uffff}>";
    static final String[] DFA16_transitionS = {
            "\2\11\2\uffff\1\11\22\uffff\1\11\1\uffff\1\6\1\10\7\uffff\1"+
            "\3\1\uffff\1\3\1\5\1\uffff\12\4\7\uffff\32\7\1\1\1\uffff\1\2"+
            "\1\uffff\1\7\1\uffff\32\7",
            "",
            "",
            "\1\5\1\uffff\12\4",
            "\1\5\1\uffff\12\4",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA16_eot = DFA.unpackEncodedString(DFA16_eotS);
    static final short[] DFA16_eof = DFA.unpackEncodedString(DFA16_eofS);
    static final char[] DFA16_min = DFA.unpackEncodedStringToUnsignedChars(DFA16_minS);
    static final char[] DFA16_max = DFA.unpackEncodedStringToUnsignedChars(DFA16_maxS);
    static final short[] DFA16_accept = DFA.unpackEncodedString(DFA16_acceptS);
    static final short[] DFA16_special = DFA.unpackEncodedString(DFA16_specialS);
    static final short[][] DFA16_transition;

    static {
        int numStates = DFA16_transitionS.length;
        DFA16_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA16_transition[i] = DFA.unpackEncodedString(DFA16_transitionS[i]);
        }
    }

    class DFA16 extends DFA {

        public DFA16(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 16;
            this.eot = DFA16_eot;
            this.eof = DFA16_eof;
            this.min = DFA16_min;
            this.max = DFA16_max;
            this.accept = DFA16_accept;
            this.special = DFA16_special;
            this.transition = DFA16_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__10 | T__11 | RULE_GML_INT | RULE_GML_FLOAT | RULE_STRING | RULE_ID | RULE_PREC_LINE | RULE_WS );";
        }
    }
 

}