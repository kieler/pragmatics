package de.cau.cs.kieler.kiml.config.text.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalLayoutConfigLexer extends Lexer {
    public static final int RULE_BOOLEAN=4;
    public static final int RULE_ID=8;
    public static final int RULE_STRING=5;
    public static final int T__15=15;
    public static final int T__12=12;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int RULE_NATURAL=7;
    public static final int RULE_WS=11;
    public static final int RULE_SL_COMMENT=10;
    public static final int EOF=-1;
    public static final int RULE_TFLOAT=6;
    public static final int RULE_ML_COMMENT=9;

    // delegates
    // delegators

    public InternalLayoutConfigLexer() {;} 
    public InternalLayoutConfigLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalLayoutConfigLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g"; }

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:11:7: ( '{' )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:11:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:12:7: ( '}' )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:12:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:13:7: ( ':' )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:13:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:14:7: ( '.' )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:14:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "RULE_TFLOAT"
    public final void mRULE_TFLOAT() throws RecognitionException {
        try {
            int _type = RULE_TFLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:13: ( ( ( '+' | '-' ) ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? ) | ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:15: ( ( '+' | '-' ) ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? ) | ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:15: ( ( '+' | '-' ) ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? ) | ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            int alt24=4;
            alt24 = dfa24.predict(input);
            switch (alt24) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:16: ( '+' | '-' ) ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? )
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:26: ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? )
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( ((LA11_0>='0' && LA11_0<='9')) ) {
                        alt11=1;
                    }
                    else if ( (LA11_0=='.') ) {
                        alt11=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 11, 0, input);

                        throw nvae;
                    }
                    switch (alt11) {
                        case 1 :
                            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:27: ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                            {
                            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:27: ( '0' .. '9' )+
                            int cnt1=0;
                            loop1:
                            do {
                                int alt1=2;
                                int LA1_0 = input.LA(1);

                                if ( ((LA1_0>='0' && LA1_0<='9')) ) {
                                    alt1=1;
                                }


                                switch (alt1) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:28: '0' .. '9'
                            	    {
                            	    matchRange('0','9'); 

                            	    }
                            	    break;

                            	default :
                            	    if ( cnt1 >= 1 ) break loop1;
                                        EarlyExitException eee =
                                            new EarlyExitException(1, input);
                                        throw eee;
                                }
                                cnt1++;
                            } while (true);

                            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:39: ( '.' ( '0' .. '9' )* )?
                            int alt3=2;
                            int LA3_0 = input.LA(1);

                            if ( (LA3_0=='.') ) {
                                alt3=1;
                            }
                            switch (alt3) {
                                case 1 :
                                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:40: '.' ( '0' .. '9' )*
                                    {
                                    match('.'); 
                                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:44: ( '0' .. '9' )*
                                    loop2:
                                    do {
                                        int alt2=2;
                                        int LA2_0 = input.LA(1);

                                        if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                                            alt2=1;
                                        }


                                        switch (alt2) {
                                    	case 1 :
                                    	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:45: '0' .. '9'
                                    	    {
                                    	    matchRange('0','9'); 

                                    	    }
                                    	    break;

                                    	default :
                                    	    break loop2;
                                        }
                                    } while (true);


                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:58: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                            int alt6=2;
                            int LA6_0 = input.LA(1);

                            if ( (LA6_0=='E'||LA6_0=='e') ) {
                                alt6=1;
                            }
                            switch (alt6) {
                                case 1 :
                                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:59: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
                                    {
                                    if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                                        input.consume();

                                    }
                                    else {
                                        MismatchedSetException mse = new MismatchedSetException(null,input);
                                        recover(mse);
                                        throw mse;}

                                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:69: ( '+' | '-' )?
                                    int alt4=2;
                                    int LA4_0 = input.LA(1);

                                    if ( (LA4_0=='+'||LA4_0=='-') ) {
                                        alt4=1;
                                    }
                                    switch (alt4) {
                                        case 1 :
                                            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:
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

                                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:80: ( '0' .. '9' )+
                                    int cnt5=0;
                                    loop5:
                                    do {
                                        int alt5=2;
                                        int LA5_0 = input.LA(1);

                                        if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                                            alt5=1;
                                        }


                                        switch (alt5) {
                                    	case 1 :
                                    	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:81: '0' .. '9'
                                    	    {
                                    	    matchRange('0','9'); 

                                    	    }
                                    	    break;

                                    	default :
                                    	    if ( cnt5 >= 1 ) break loop5;
                                                EarlyExitException eee =
                                                    new EarlyExitException(5, input);
                                                throw eee;
                                        }
                                        cnt5++;
                                    } while (true);


                                    }
                                    break;

                            }


                            }
                            break;
                        case 2 :
                            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:94: '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                            {
                            match('.'); 
                            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:98: ( '0' .. '9' )+
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
                            	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:99: '0' .. '9'
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

                            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:110: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                            int alt10=2;
                            int LA10_0 = input.LA(1);

                            if ( (LA10_0=='E'||LA10_0=='e') ) {
                                alt10=1;
                            }
                            switch (alt10) {
                                case 1 :
                                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:111: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
                                    {
                                    if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                                        input.consume();

                                    }
                                    else {
                                        MismatchedSetException mse = new MismatchedSetException(null,input);
                                        recover(mse);
                                        throw mse;}

                                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:121: ( '+' | '-' )?
                                    int alt8=2;
                                    int LA8_0 = input.LA(1);

                                    if ( (LA8_0=='+'||LA8_0=='-') ) {
                                        alt8=1;
                                    }
                                    switch (alt8) {
                                        case 1 :
                                            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:
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

                                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:132: ( '0' .. '9' )+
                                    int cnt9=0;
                                    loop9:
                                    do {
                                        int alt9=2;
                                        int LA9_0 = input.LA(1);

                                        if ( ((LA9_0>='0' && LA9_0<='9')) ) {
                                            alt9=1;
                                        }


                                        switch (alt9) {
                                    	case 1 :
                                    	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:133: '0' .. '9'
                                    	    {
                                    	    matchRange('0','9'); 

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


                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:147: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                    {
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:147: ( '0' .. '9' )+
                    int cnt12=0;
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( ((LA12_0>='0' && LA12_0<='9')) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:148: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt12 >= 1 ) break loop12;
                                EarlyExitException eee =
                                    new EarlyExitException(12, input);
                                throw eee;
                        }
                        cnt12++;
                    } while (true);

                    match('.'); 
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:163: ( '0' .. '9' )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( ((LA13_0>='0' && LA13_0<='9')) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:164: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);

                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:175: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0=='E'||LA16_0=='e') ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:176: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
                            {
                            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}

                            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:186: ( '+' | '-' )?
                            int alt14=2;
                            int LA14_0 = input.LA(1);

                            if ( (LA14_0=='+'||LA14_0=='-') ) {
                                alt14=1;
                            }
                            switch (alt14) {
                                case 1 :
                                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:
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

                            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:197: ( '0' .. '9' )+
                            int cnt15=0;
                            loop15:
                            do {
                                int alt15=2;
                                int LA15_0 = input.LA(1);

                                if ( ((LA15_0>='0' && LA15_0<='9')) ) {
                                    alt15=1;
                                }


                                switch (alt15) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:198: '0' .. '9'
                            	    {
                            	    matchRange('0','9'); 

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
                            break;

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:211: '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                    {
                    match('.'); 
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:215: ( '0' .. '9' )+
                    int cnt17=0;
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( ((LA17_0>='0' && LA17_0<='9')) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:216: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt17 >= 1 ) break loop17;
                                EarlyExitException eee =
                                    new EarlyExitException(17, input);
                                throw eee;
                        }
                        cnt17++;
                    } while (true);

                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:227: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0=='E'||LA20_0=='e') ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:228: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
                            {
                            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}

                            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:238: ( '+' | '-' )?
                            int alt18=2;
                            int LA18_0 = input.LA(1);

                            if ( (LA18_0=='+'||LA18_0=='-') ) {
                                alt18=1;
                            }
                            switch (alt18) {
                                case 1 :
                                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:
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

                            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:249: ( '0' .. '9' )+
                            int cnt19=0;
                            loop19:
                            do {
                                int alt19=2;
                                int LA19_0 = input.LA(1);

                                if ( ((LA19_0>='0' && LA19_0<='9')) ) {
                                    alt19=1;
                                }


                                switch (alt19) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:250: '0' .. '9'
                            	    {
                            	    matchRange('0','9'); 

                            	    }
                            	    break;

                            	default :
                            	    if ( cnt19 >= 1 ) break loop19;
                                        EarlyExitException eee =
                                            new EarlyExitException(19, input);
                                        throw eee;
                                }
                                cnt19++;
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:263: ( '0' .. '9' )+ ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
                    {
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:263: ( '0' .. '9' )+
                    int cnt21=0;
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( ((LA21_0>='0' && LA21_0<='9')) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:264: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt21 >= 1 ) break loop21;
                                EarlyExitException eee =
                                    new EarlyExitException(21, input);
                                throw eee;
                        }
                        cnt21++;
                    } while (true);

                    if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:285: ( '+' | '-' )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0=='+'||LA22_0=='-') ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:
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

                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:296: ( '0' .. '9' )+
                    int cnt23=0;
                    loop23:
                    do {
                        int alt23=2;
                        int LA23_0 = input.LA(1);

                        if ( ((LA23_0>='0' && LA23_0<='9')) ) {
                            alt23=1;
                        }


                        switch (alt23) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:880:297: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt23 >= 1 ) break loop23;
                                EarlyExitException eee =
                                    new EarlyExitException(23, input);
                                throw eee;
                        }
                        cnt23++;
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
    // $ANTLR end "RULE_TFLOAT"

    // $ANTLR start "RULE_NATURAL"
    public final void mRULE_NATURAL() throws RecognitionException {
        try {
            int _type = RULE_NATURAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:882:14: ( ( '0' .. '9' )+ )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:882:16: ( '0' .. '9' )+
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:882:16: ( '0' .. '9' )+
            int cnt25=0;
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( ((LA25_0>='0' && LA25_0<='9')) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:882:17: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt25 >= 1 ) break loop25;
                        EarlyExitException eee =
                            new EarlyExitException(25, input);
                        throw eee;
                }
                cnt25++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_NATURAL"

    // $ANTLR start "RULE_BOOLEAN"
    public final void mRULE_BOOLEAN() throws RecognitionException {
        try {
            int _type = RULE_BOOLEAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:884:14: ( ( 'true' | 'false' ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:884:16: ( 'true' | 'false' )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:884:16: ( 'true' | 'false' )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0=='t') ) {
                alt26=1;
            }
            else if ( (LA26_0=='f') ) {
                alt26=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:884:17: 'true'
                    {
                    match("true"); 


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:884:24: 'false'
                    {
                    match("false"); 


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
    // $ANTLR end "RULE_BOOLEAN"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:886:13: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:886:15: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
            {
            match('\"'); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:886:19: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
            loop27:
            do {
                int alt27=3;
                int LA27_0 = input.LA(1);

                if ( (LA27_0=='\\') ) {
                    alt27=1;
                }
                else if ( ((LA27_0>='\u0000' && LA27_0<='!')||(LA27_0>='#' && LA27_0<='[')||(LA27_0>=']' && LA27_0<='\uFFFF')) ) {
                    alt27=2;
                }


                switch (alt27) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:886:20: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
            	    {
            	    match('\\'); 
            	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||(input.LA(1)>='t' && input.LA(1)<='u') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;
            	case 2 :
            	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:886:65: ~ ( ( '\\\\' | '\"' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop27;
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
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:888:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:888:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:888:11: ( '^' )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0=='^') ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:888:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:888:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( ((LA29_0>='0' && LA29_0<='9')||(LA29_0>='A' && LA29_0<='Z')||LA29_0=='_'||(LA29_0>='a' && LA29_0<='z')) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:
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
            	    break loop29;
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

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:890:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:890:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:890:24: ( options {greedy=false; } : . )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0=='*') ) {
                    int LA30_1 = input.LA(2);

                    if ( (LA30_1=='/') ) {
                        alt30=2;
                    }
                    else if ( ((LA30_1>='\u0000' && LA30_1<='.')||(LA30_1>='0' && LA30_1<='\uFFFF')) ) {
                        alt30=1;
                    }


                }
                else if ( ((LA30_0>='\u0000' && LA30_0<=')')||(LA30_0>='+' && LA30_0<='\uFFFF')) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:890:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:892:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:892:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:892:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( ((LA31_0>='\u0000' && LA31_0<='\t')||(LA31_0>='\u000B' && LA31_0<='\f')||(LA31_0>='\u000E' && LA31_0<='\uFFFF')) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:892:24: ~ ( ( '\\n' | '\\r' ) )
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
            	    break loop31;
                }
            } while (true);

            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:892:40: ( ( '\\r' )? '\\n' )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0=='\n'||LA33_0=='\r') ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:892:41: ( '\\r' )? '\\n'
                    {
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:892:41: ( '\\r' )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0=='\r') ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:892:41: '\\r'
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
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:894:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:894:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:894:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt34=0;
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( ((LA34_0>='\t' && LA34_0<='\n')||LA34_0=='\r'||LA34_0==' ') ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:
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
            	    if ( cnt34 >= 1 ) break loop34;
                        EarlyExitException eee =
                            new EarlyExitException(34, input);
                        throw eee;
                }
                cnt34++;
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
        // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:1:8: ( T__12 | T__13 | T__14 | T__15 | RULE_TFLOAT | RULE_NATURAL | RULE_BOOLEAN | RULE_STRING | RULE_ID | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS )
        int alt35=12;
        alt35 = dfa35.predict(input);
        switch (alt35) {
            case 1 :
                // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:1:10: T__12
                {
                mT__12(); 

                }
                break;
            case 2 :
                // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:1:16: T__13
                {
                mT__13(); 

                }
                break;
            case 3 :
                // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:1:22: T__14
                {
                mT__14(); 

                }
                break;
            case 4 :
                // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:1:28: T__15
                {
                mT__15(); 

                }
                break;
            case 5 :
                // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:1:34: RULE_TFLOAT
                {
                mRULE_TFLOAT(); 

                }
                break;
            case 6 :
                // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:1:46: RULE_NATURAL
                {
                mRULE_NATURAL(); 

                }
                break;
            case 7 :
                // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:1:59: RULE_BOOLEAN
                {
                mRULE_BOOLEAN(); 

                }
                break;
            case 8 :
                // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:1:72: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 9 :
                // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:1:84: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 10 :
                // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:1:92: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 11 :
                // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:1:108: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 12 :
                // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:1:124: RULE_WS
                {
                mRULE_WS(); 

                }
                break;

        }

    }


    protected DFA24 dfa24 = new DFA24(this);
    protected DFA35 dfa35 = new DFA35(this);
    static final String DFA24_eotS =
        "\6\uffff";
    static final String DFA24_eofS =
        "\6\uffff";
    static final String DFA24_minS =
        "\1\53\1\uffff\1\56\3\uffff";
    static final String DFA24_maxS =
        "\1\71\1\uffff\1\145\3\uffff";
    static final String DFA24_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\1\2\1\4";
    static final String DFA24_specialS =
        "\6\uffff}>";
    static final String[] DFA24_transitionS = {
            "\1\1\1\uffff\1\1\1\3\1\uffff\12\2",
            "",
            "\1\4\1\uffff\12\2\13\uffff\1\5\37\uffff\1\5",
            "",
            "",
            ""
    };

    static final short[] DFA24_eot = DFA.unpackEncodedString(DFA24_eotS);
    static final short[] DFA24_eof = DFA.unpackEncodedString(DFA24_eofS);
    static final char[] DFA24_min = DFA.unpackEncodedStringToUnsignedChars(DFA24_minS);
    static final char[] DFA24_max = DFA.unpackEncodedStringToUnsignedChars(DFA24_maxS);
    static final short[] DFA24_accept = DFA.unpackEncodedString(DFA24_acceptS);
    static final short[] DFA24_special = DFA.unpackEncodedString(DFA24_specialS);
    static final short[][] DFA24_transition;

    static {
        int numStates = DFA24_transitionS.length;
        DFA24_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA24_transition[i] = DFA.unpackEncodedString(DFA24_transitionS[i]);
        }
    }

    class DFA24 extends DFA {

        public DFA24(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 24;
            this.eot = DFA24_eot;
            this.eof = DFA24_eof;
            this.min = DFA24_min;
            this.max = DFA24_max;
            this.accept = DFA24_accept;
            this.special = DFA24_special;
            this.transition = DFA24_transition;
        }
        public String getDescription() {
            return "880:15: ( ( '+' | '-' ) ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? ) | ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )";
        }
    }
    static final String DFA35_eotS =
        "\4\uffff\1\15\1\uffff\1\16\2\12\6\uffff\2\12\2\uffff\2\12\1\27"+
        "\1\12\1\uffff\1\27";
    static final String DFA35_eofS =
        "\31\uffff";
    static final String DFA35_minS =
        "\1\11\3\uffff\1\60\1\uffff\1\56\1\162\1\141\2\uffff\1\52\3\uffff"+
        "\1\165\1\154\2\uffff\1\145\1\163\1\60\1\145\1\uffff\1\60";
    static final String DFA35_maxS =
        "\1\175\3\uffff\1\71\1\uffff\1\145\1\162\1\141\2\uffff\1\57\3\uffff"+
        "\1\165\1\154\2\uffff\1\145\1\163\1\172\1\145\1\uffff\1\172";
    static final String DFA35_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\uffff\1\5\3\uffff\1\10\1\11\1\uffff\1\14"+
        "\1\4\1\6\2\uffff\1\12\1\13\4\uffff\1\7\1\uffff";
    static final String DFA35_specialS =
        "\31\uffff}>";
    static final String[] DFA35_transitionS = {
            "\2\14\2\uffff\1\14\22\uffff\1\14\1\uffff\1\11\10\uffff\1\5"+
            "\1\uffff\1\5\1\4\1\13\12\6\1\3\6\uffff\32\12\3\uffff\2\12\1"+
            "\uffff\5\12\1\10\15\12\1\7\6\12\1\1\1\uffff\1\2",
            "",
            "",
            "",
            "\12\5",
            "",
            "\1\5\1\uffff\12\6\13\uffff\1\5\37\uffff\1\5",
            "\1\17",
            "\1\20",
            "",
            "",
            "\1\21\4\uffff\1\22",
            "",
            "",
            "",
            "\1\23",
            "\1\24",
            "",
            "",
            "\1\25",
            "\1\26",
            "\12\12\7\uffff\32\12\4\uffff\1\12\1\uffff\32\12",
            "\1\30",
            "",
            "\12\12\7\uffff\32\12\4\uffff\1\12\1\uffff\32\12"
    };

    static final short[] DFA35_eot = DFA.unpackEncodedString(DFA35_eotS);
    static final short[] DFA35_eof = DFA.unpackEncodedString(DFA35_eofS);
    static final char[] DFA35_min = DFA.unpackEncodedStringToUnsignedChars(DFA35_minS);
    static final char[] DFA35_max = DFA.unpackEncodedStringToUnsignedChars(DFA35_maxS);
    static final short[] DFA35_accept = DFA.unpackEncodedString(DFA35_acceptS);
    static final short[] DFA35_special = DFA.unpackEncodedString(DFA35_specialS);
    static final short[][] DFA35_transition;

    static {
        int numStates = DFA35_transitionS.length;
        DFA35_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA35_transition[i] = DFA.unpackEncodedString(DFA35_transitionS[i]);
        }
    }

    class DFA35 extends DFA {

        public DFA35(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 35;
            this.eot = DFA35_eot;
            this.eof = DFA35_eof;
            this.min = DFA35_min;
            this.max = DFA35_max;
            this.accept = DFA35_accept;
            this.special = DFA35_special;
            this.transition = DFA35_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__12 | T__13 | T__14 | T__15 | RULE_TFLOAT | RULE_NATURAL | RULE_BOOLEAN | RULE_STRING | RULE_ID | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS );";
        }
    }
 

}