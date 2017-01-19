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
    public static final int RULE_STRING=7;
    public static final int RULE_SL_COMMENT=10;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_ID=6;
    public static final int RULE_WS=11;
    public static final int RULE_SIGNED_INT=4;
    public static final int RULE_ANY_OTHER=12;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=8;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=9;
    public static final int T__23=23;
    public static final int RULE_FLOAT=5;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators

    public InternalLayoutConfigLexer() {;} 
    public InternalLayoutConfigLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalLayoutConfigLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "InternalLayoutConfig.g"; }

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:11:7: ( 'true' )
            // InternalLayoutConfig.g:11:9: 'true'
            {
            match("true"); 


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
            // InternalLayoutConfig.g:12:7: ( 'false' )
            // InternalLayoutConfig.g:12:9: 'false'
            {
            match("false"); 


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
            // InternalLayoutConfig.g:13:7: ( '{' )
            // InternalLayoutConfig.g:13:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:14:7: ( '}' )
            // InternalLayoutConfig.g:14:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:15:7: ( 'ref' )
            // InternalLayoutConfig.g:15:9: 'ref'
            {
            match("ref"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:16:7: ( 'label' )
            // InternalLayoutConfig.g:16:9: 'label'
            {
            match("label"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:17:7: ( ':' )
            // InternalLayoutConfig.g:17:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:18:7: ( 'layout' )
            // InternalLayoutConfig.g:18:9: 'layout'
            {
            match("layout"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:19:7: ( '[' )
            // InternalLayoutConfig.g:19:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:20:7: ( ']' )
            // InternalLayoutConfig.g:20:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:21:7: ( 'position' )
            // InternalLayoutConfig.g:21:9: 'position'
            {
            match("position"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:22:7: ( ',' )
            // InternalLayoutConfig.g:22:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:23:7: ( 'size' )
            // InternalLayoutConfig.g:23:9: 'size'
            {
            match("size"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:24:7: ( 'incoming' )
            // InternalLayoutConfig.g:24:9: 'incoming'
            {
            match("incoming"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:25:7: ( 'outgoing' )
            // InternalLayoutConfig.g:25:9: 'outgoing'
            {
            match("outgoing"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:26:7: ( 'start' )
            // InternalLayoutConfig.g:26:9: 'start'
            {
            match("start"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:27:7: ( 'end' )
            // InternalLayoutConfig.g:27:9: 'end'
            {
            match("end"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:28:7: ( 'bends' )
            // InternalLayoutConfig.g:28:9: 'bends'
            {
            match("bends"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:29:7: ( '|' )
            // InternalLayoutConfig.g:29:9: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:30:7: ( 'section' )
            // InternalLayoutConfig.g:30:9: 'section'
            {
            match("section"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:31:7: ( '->' )
            // InternalLayoutConfig.g:31:9: '->'
            {
            match("->"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:32:7: ( '.' )
            // InternalLayoutConfig.g:32:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "RULE_SIGNED_INT"
    public final void mRULE_SIGNED_INT() throws RecognitionException {
        try {
            int _type = RULE_SIGNED_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:5328:17: ( ( '+' | '-' )? RULE_INT )
            // InternalLayoutConfig.g:5328:19: ( '+' | '-' )? RULE_INT
            {
            // InternalLayoutConfig.g:5328:19: ( '+' | '-' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='+'||LA1_0=='-') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // InternalLayoutConfig.g:
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

            mRULE_INT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SIGNED_INT"

    // $ANTLR start "RULE_FLOAT"
    public final void mRULE_FLOAT() throws RecognitionException {
        try {
            int _type = RULE_FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:5330:12: ( ( '+' | '-' )? ( RULE_INT '.' RULE_INT | RULE_INT ( '.' RULE_INT )? ( 'e' | 'E' ) ( '+' | '-' )? RULE_INT ) )
            // InternalLayoutConfig.g:5330:14: ( '+' | '-' )? ( RULE_INT '.' RULE_INT | RULE_INT ( '.' RULE_INT )? ( 'e' | 'E' ) ( '+' | '-' )? RULE_INT )
            {
            // InternalLayoutConfig.g:5330:14: ( '+' | '-' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='+'||LA2_0=='-') ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalLayoutConfig.g:
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

            // InternalLayoutConfig.g:5330:25: ( RULE_INT '.' RULE_INT | RULE_INT ( '.' RULE_INT )? ( 'e' | 'E' ) ( '+' | '-' )? RULE_INT )
            int alt5=2;
            alt5 = dfa5.predict(input);
            switch (alt5) {
                case 1 :
                    // InternalLayoutConfig.g:5330:26: RULE_INT '.' RULE_INT
                    {
                    mRULE_INT(); 
                    match('.'); 
                    mRULE_INT(); 

                    }
                    break;
                case 2 :
                    // InternalLayoutConfig.g:5330:48: RULE_INT ( '.' RULE_INT )? ( 'e' | 'E' ) ( '+' | '-' )? RULE_INT
                    {
                    mRULE_INT(); 
                    // InternalLayoutConfig.g:5330:57: ( '.' RULE_INT )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0=='.') ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // InternalLayoutConfig.g:5330:58: '.' RULE_INT
                            {
                            match('.'); 
                            mRULE_INT(); 

                            }
                            break;

                    }

                    if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // InternalLayoutConfig.g:5330:83: ( '+' | '-' )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0=='+'||LA4_0=='-') ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // InternalLayoutConfig.g:
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

                    mRULE_INT(); 

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
    // $ANTLR end "RULE_FLOAT"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:5332:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // InternalLayoutConfig.g:5332:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // InternalLayoutConfig.g:5332:11: ( '^' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='^') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalLayoutConfig.g:5332:11: '^'
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

            // InternalLayoutConfig.g:5332:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='0' && LA7_0<='9')||(LA7_0>='A' && LA7_0<='Z')||LA7_0=='_'||(LA7_0>='a' && LA7_0<='z')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalLayoutConfig.g:
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
            	    break loop7;
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

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:5334:19: ( ( '0' .. '9' )+ )
            // InternalLayoutConfig.g:5334:21: ( '0' .. '9' )+
            {
            // InternalLayoutConfig.g:5334:21: ( '0' .. '9' )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='0' && LA8_0<='9')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalLayoutConfig.g:5334:22: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:5336:13: ( ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // InternalLayoutConfig.g:5336:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // InternalLayoutConfig.g:5336:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='\"') ) {
                alt11=1;
            }
            else if ( (LA11_0=='\'') ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // InternalLayoutConfig.g:5336:16: '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // InternalLayoutConfig.g:5336:20: ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop9:
                    do {
                        int alt9=3;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0=='\\') ) {
                            alt9=1;
                        }
                        else if ( ((LA9_0>='\u0000' && LA9_0<='!')||(LA9_0>='#' && LA9_0<='[')||(LA9_0>=']' && LA9_0<='\uFFFF')) ) {
                            alt9=2;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // InternalLayoutConfig.g:5336:21: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalLayoutConfig.g:5336:28: ~ ( ( '\\\\' | '\"' ) )
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
                    	    break loop9;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // InternalLayoutConfig.g:5336:48: '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // InternalLayoutConfig.g:5336:53: ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop10:
                    do {
                        int alt10=3;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0=='\\') ) {
                            alt10=1;
                        }
                        else if ( ((LA10_0>='\u0000' && LA10_0<='&')||(LA10_0>='(' && LA10_0<='[')||(LA10_0>=']' && LA10_0<='\uFFFF')) ) {
                            alt10=2;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // InternalLayoutConfig.g:5336:54: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalLayoutConfig.g:5336:61: ~ ( ( '\\\\' | '\\'' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
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

                    match('\''); 

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
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:5338:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // InternalLayoutConfig.g:5338:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // InternalLayoutConfig.g:5338:24: ( options {greedy=false; } : . )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0=='*') ) {
                    int LA12_1 = input.LA(2);

                    if ( (LA12_1=='/') ) {
                        alt12=2;
                    }
                    else if ( ((LA12_1>='\u0000' && LA12_1<='.')||(LA12_1>='0' && LA12_1<='\uFFFF')) ) {
                        alt12=1;
                    }


                }
                else if ( ((LA12_0>='\u0000' && LA12_0<=')')||(LA12_0>='+' && LA12_0<='\uFFFF')) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalLayoutConfig.g:5338:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop12;
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
            // InternalLayoutConfig.g:5340:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // InternalLayoutConfig.g:5340:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // InternalLayoutConfig.g:5340:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>='\u0000' && LA13_0<='\t')||(LA13_0>='\u000B' && LA13_0<='\f')||(LA13_0>='\u000E' && LA13_0<='\uFFFF')) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalLayoutConfig.g:5340:24: ~ ( ( '\\n' | '\\r' ) )
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
            	    break loop13;
                }
            } while (true);

            // InternalLayoutConfig.g:5340:40: ( ( '\\r' )? '\\n' )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0=='\n'||LA15_0=='\r') ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalLayoutConfig.g:5340:41: ( '\\r' )? '\\n'
                    {
                    // InternalLayoutConfig.g:5340:41: ( '\\r' )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0=='\r') ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // InternalLayoutConfig.g:5340:41: '\\r'
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
            // InternalLayoutConfig.g:5342:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // InternalLayoutConfig.g:5342:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // InternalLayoutConfig.g:5342:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>='\t' && LA16_0<='\n')||LA16_0=='\r'||LA16_0==' ') ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalLayoutConfig.g:
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
            	    if ( cnt16 >= 1 ) break loop16;
                        EarlyExitException eee =
                            new EarlyExitException(16, input);
                        throw eee;
                }
                cnt16++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLayoutConfig.g:5344:16: ( . )
            // InternalLayoutConfig.g:5344:18: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    public void mTokens() throws RecognitionException {
        // InternalLayoutConfig.g:1:8: ( T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | RULE_SIGNED_INT | RULE_FLOAT | RULE_ID | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt17=30;
        alt17 = dfa17.predict(input);
        switch (alt17) {
            case 1 :
                // InternalLayoutConfig.g:1:10: T__13
                {
                mT__13(); 

                }
                break;
            case 2 :
                // InternalLayoutConfig.g:1:16: T__14
                {
                mT__14(); 

                }
                break;
            case 3 :
                // InternalLayoutConfig.g:1:22: T__15
                {
                mT__15(); 

                }
                break;
            case 4 :
                // InternalLayoutConfig.g:1:28: T__16
                {
                mT__16(); 

                }
                break;
            case 5 :
                // InternalLayoutConfig.g:1:34: T__17
                {
                mT__17(); 

                }
                break;
            case 6 :
                // InternalLayoutConfig.g:1:40: T__18
                {
                mT__18(); 

                }
                break;
            case 7 :
                // InternalLayoutConfig.g:1:46: T__19
                {
                mT__19(); 

                }
                break;
            case 8 :
                // InternalLayoutConfig.g:1:52: T__20
                {
                mT__20(); 

                }
                break;
            case 9 :
                // InternalLayoutConfig.g:1:58: T__21
                {
                mT__21(); 

                }
                break;
            case 10 :
                // InternalLayoutConfig.g:1:64: T__22
                {
                mT__22(); 

                }
                break;
            case 11 :
                // InternalLayoutConfig.g:1:70: T__23
                {
                mT__23(); 

                }
                break;
            case 12 :
                // InternalLayoutConfig.g:1:76: T__24
                {
                mT__24(); 

                }
                break;
            case 13 :
                // InternalLayoutConfig.g:1:82: T__25
                {
                mT__25(); 

                }
                break;
            case 14 :
                // InternalLayoutConfig.g:1:88: T__26
                {
                mT__26(); 

                }
                break;
            case 15 :
                // InternalLayoutConfig.g:1:94: T__27
                {
                mT__27(); 

                }
                break;
            case 16 :
                // InternalLayoutConfig.g:1:100: T__28
                {
                mT__28(); 

                }
                break;
            case 17 :
                // InternalLayoutConfig.g:1:106: T__29
                {
                mT__29(); 

                }
                break;
            case 18 :
                // InternalLayoutConfig.g:1:112: T__30
                {
                mT__30(); 

                }
                break;
            case 19 :
                // InternalLayoutConfig.g:1:118: T__31
                {
                mT__31(); 

                }
                break;
            case 20 :
                // InternalLayoutConfig.g:1:124: T__32
                {
                mT__32(); 

                }
                break;
            case 21 :
                // InternalLayoutConfig.g:1:130: T__33
                {
                mT__33(); 

                }
                break;
            case 22 :
                // InternalLayoutConfig.g:1:136: T__34
                {
                mT__34(); 

                }
                break;
            case 23 :
                // InternalLayoutConfig.g:1:142: RULE_SIGNED_INT
                {
                mRULE_SIGNED_INT(); 

                }
                break;
            case 24 :
                // InternalLayoutConfig.g:1:158: RULE_FLOAT
                {
                mRULE_FLOAT(); 

                }
                break;
            case 25 :
                // InternalLayoutConfig.g:1:169: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 26 :
                // InternalLayoutConfig.g:1:177: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 27 :
                // InternalLayoutConfig.g:1:189: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 28 :
                // InternalLayoutConfig.g:1:205: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 29 :
                // InternalLayoutConfig.g:1:221: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 30 :
                // InternalLayoutConfig.g:1:229: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA5 dfa5 = new DFA5(this);
    protected DFA17 dfa17 = new DFA17(this);
    static final String DFA5_eotS =
        "\4\uffff\1\5\1\uffff";
    static final String DFA5_eofS =
        "\6\uffff";
    static final String DFA5_minS =
        "\1\60\1\56\1\60\1\uffff\1\60\1\uffff";
    static final String DFA5_maxS =
        "\1\71\1\145\1\71\1\uffff\1\145\1\uffff";
    static final String DFA5_acceptS =
        "\3\uffff\1\2\1\uffff\1\1";
    static final String DFA5_specialS =
        "\6\uffff}>";
    static final String[] DFA5_transitionS = {
            "\12\1",
            "\1\2\1\uffff\12\1\13\uffff\1\3\37\uffff\1\3",
            "\12\4",
            "",
            "\12\4\13\uffff\1\3\37\uffff\1\3",
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    static class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "5330:25: ( RULE_INT '.' RULE_INT | RULE_INT ( '.' RULE_INT )? ( 'e' | 'E' ) ( '+' | '-' )? RULE_INT )";
        }
    }
    static final String DFA17_eotS =
        "\1\uffff\2\36\2\uffff\2\36\3\uffff\1\36\1\uffff\5\36\1\uffff\1\34\1\uffff\1\34\1\64\1\34\1\uffff\3\34\2\uffff\1\36\1\uffff\1\36\2\uffff\2\36\3\uffff\1\36\1\uffff\7\36\2\uffff\1\64\7\uffff\2\36\1\111\10\36\1\122\1\36\1\124\1\36\1\uffff\3\36\1\131\4\36\1\uffff\1\36\1\uffff\1\137\1\140\2\36\1\uffff\1\143\3\36\1\147\2\uffff\1\150\1\36\1\uffff\3\36\2\uffff\1\36\1\156\2\36\1\161\1\uffff\1\162\1\163\3\uffff";
    static final String DFA17_eofS =
        "\164\uffff";
    static final String DFA17_minS =
        "\1\0\1\162\1\141\2\uffff\1\145\1\141\3\uffff\1\157\1\uffff\1\145\1\156\1\165\1\156\1\145\1\uffff\1\60\1\uffff\1\60\1\56\1\101\1\uffff\2\0\1\52\2\uffff\1\165\1\uffff\1\154\2\uffff\1\146\1\142\3\uffff\1\163\1\uffff\1\172\1\141\2\143\1\164\1\144\1\156\2\uffff\1\56\7\uffff\1\145\1\163\1\60\1\145\1\157\1\151\1\145\1\162\1\164\1\157\1\147\1\60\1\144\1\60\1\145\1\uffff\1\154\1\165\1\164\1\60\1\164\1\151\1\155\1\157\1\uffff\1\163\1\uffff\2\60\1\164\1\151\1\uffff\1\60\1\157\2\151\1\60\2\uffff\1\60\1\157\1\uffff\3\156\2\uffff\1\156\1\60\2\147\1\60\1\uffff\2\60\3\uffff";
    static final String DFA17_maxS =
        "\1\uffff\1\162\1\141\2\uffff\1\145\1\141\3\uffff\1\157\1\uffff\1\164\1\156\1\165\1\156\1\145\1\uffff\1\76\1\uffff\1\71\1\145\1\172\1\uffff\2\uffff\1\57\2\uffff\1\165\1\uffff\1\154\2\uffff\1\146\1\171\3\uffff\1\163\1\uffff\1\172\1\141\2\143\1\164\1\144\1\156\2\uffff\1\145\7\uffff\1\145\1\163\1\172\1\145\1\157\1\151\1\145\1\162\1\164\1\157\1\147\1\172\1\144\1\172\1\145\1\uffff\1\154\1\165\1\164\1\172\1\164\1\151\1\155\1\157\1\uffff\1\163\1\uffff\2\172\1\164\1\151\1\uffff\1\172\1\157\2\151\1\172\2\uffff\1\172\1\157\1\uffff\3\156\2\uffff\1\156\1\172\2\147\1\172\1\uffff\2\172\3\uffff";
    static final String DFA17_acceptS =
        "\3\uffff\1\3\1\4\2\uffff\1\7\1\11\1\12\1\uffff\1\14\5\uffff\1\23\1\uffff\1\26\3\uffff\1\31\3\uffff\1\35\1\36\1\uffff\1\31\1\uffff\1\3\1\4\2\uffff\1\7\1\11\1\12\1\uffff\1\14\7\uffff\1\23\1\25\1\uffff\1\26\1\27\1\30\1\32\1\33\1\34\1\35\17\uffff\1\5\10\uffff\1\21\1\uffff\1\1\4\uffff\1\15\5\uffff\1\2\1\6\2\uffff\1\20\3\uffff\1\22\1\10\5\uffff\1\24\2\uffff\1\13\1\16\1\17";
    static final String DFA17_specialS =
        "\1\1\27\uffff\1\2\1\0\132\uffff}>";
    static final String[] DFA17_transitionS = {
            "\11\34\2\33\2\34\1\33\22\34\1\33\1\34\1\30\4\34\1\31\3\34\1\24\1\13\1\22\1\23\1\32\12\25\1\7\6\34\32\27\1\10\1\34\1\11\1\26\1\27\1\34\1\27\1\20\2\27\1\17\1\2\2\27\1\15\2\27\1\6\2\27\1\16\1\12\1\27\1\5\1\14\1\1\6\27\1\3\1\21\1\4\uff82\34",
            "\1\35",
            "\1\37",
            "",
            "",
            "\1\42",
            "\1\43",
            "",
            "",
            "",
            "\1\47",
            "",
            "\1\53\3\uffff\1\51\12\uffff\1\52",
            "\1\54",
            "\1\55",
            "\1\56",
            "\1\57",
            "",
            "\12\62\4\uffff\1\61",
            "",
            "\12\62",
            "\1\65\1\uffff\12\62\13\uffff\1\65\37\uffff\1\65",
            "\32\36\4\uffff\1\36\1\uffff\32\36",
            "",
            "\0\66",
            "\0\66",
            "\1\67\4\uffff\1\70",
            "",
            "",
            "\1\72",
            "",
            "\1\73",
            "",
            "",
            "\1\74",
            "\1\75\26\uffff\1\76",
            "",
            "",
            "",
            "\1\77",
            "",
            "\1\100",
            "\1\101",
            "\1\102",
            "\1\103",
            "\1\104",
            "\1\105",
            "\1\106",
            "",
            "",
            "\1\65\1\uffff\12\62\13\uffff\1\65\37\uffff\1\65",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\107",
            "\1\110",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\112",
            "\1\113",
            "\1\114",
            "\1\115",
            "\1\116",
            "\1\117",
            "\1\120",
            "\1\121",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\123",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\125",
            "",
            "\1\126",
            "\1\127",
            "\1\130",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\132",
            "\1\133",
            "\1\134",
            "\1\135",
            "",
            "\1\136",
            "",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\141",
            "\1\142",
            "",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\144",
            "\1\145",
            "\1\146",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "",
            "",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\151",
            "",
            "\1\152",
            "\1\153",
            "\1\154",
            "",
            "",
            "\1\155",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\157",
            "\1\160",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "",
            "",
            ""
    };

    static final short[] DFA17_eot = DFA.unpackEncodedString(DFA17_eotS);
    static final short[] DFA17_eof = DFA.unpackEncodedString(DFA17_eofS);
    static final char[] DFA17_min = DFA.unpackEncodedStringToUnsignedChars(DFA17_minS);
    static final char[] DFA17_max = DFA.unpackEncodedStringToUnsignedChars(DFA17_maxS);
    static final short[] DFA17_accept = DFA.unpackEncodedString(DFA17_acceptS);
    static final short[] DFA17_special = DFA.unpackEncodedString(DFA17_specialS);
    static final short[][] DFA17_transition;

    static {
        int numStates = DFA17_transitionS.length;
        DFA17_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA17_transition[i] = DFA.unpackEncodedString(DFA17_transitionS[i]);
        }
    }

    class DFA17 extends DFA {

        public DFA17(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 17;
            this.eot = DFA17_eot;
            this.eof = DFA17_eof;
            this.min = DFA17_min;
            this.max = DFA17_max;
            this.accept = DFA17_accept;
            this.special = DFA17_special;
            this.transition = DFA17_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | RULE_SIGNED_INT | RULE_FLOAT | RULE_ID | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA17_25 = input.LA(1);

                        s = -1;
                        if ( ((LA17_25>='\u0000' && LA17_25<='\uFFFF')) ) {s = 54;}

                        else s = 28;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA17_0 = input.LA(1);

                        s = -1;
                        if ( (LA17_0=='t') ) {s = 1;}

                        else if ( (LA17_0=='f') ) {s = 2;}

                        else if ( (LA17_0=='{') ) {s = 3;}

                        else if ( (LA17_0=='}') ) {s = 4;}

                        else if ( (LA17_0=='r') ) {s = 5;}

                        else if ( (LA17_0=='l') ) {s = 6;}

                        else if ( (LA17_0==':') ) {s = 7;}

                        else if ( (LA17_0=='[') ) {s = 8;}

                        else if ( (LA17_0==']') ) {s = 9;}

                        else if ( (LA17_0=='p') ) {s = 10;}

                        else if ( (LA17_0==',') ) {s = 11;}

                        else if ( (LA17_0=='s') ) {s = 12;}

                        else if ( (LA17_0=='i') ) {s = 13;}

                        else if ( (LA17_0=='o') ) {s = 14;}

                        else if ( (LA17_0=='e') ) {s = 15;}

                        else if ( (LA17_0=='b') ) {s = 16;}

                        else if ( (LA17_0=='|') ) {s = 17;}

                        else if ( (LA17_0=='-') ) {s = 18;}

                        else if ( (LA17_0=='.') ) {s = 19;}

                        else if ( (LA17_0=='+') ) {s = 20;}

                        else if ( ((LA17_0>='0' && LA17_0<='9')) ) {s = 21;}

                        else if ( (LA17_0=='^') ) {s = 22;}

                        else if ( ((LA17_0>='A' && LA17_0<='Z')||LA17_0=='_'||LA17_0=='a'||(LA17_0>='c' && LA17_0<='d')||(LA17_0>='g' && LA17_0<='h')||(LA17_0>='j' && LA17_0<='k')||(LA17_0>='m' && LA17_0<='n')||LA17_0=='q'||(LA17_0>='u' && LA17_0<='z')) ) {s = 23;}

                        else if ( (LA17_0=='\"') ) {s = 24;}

                        else if ( (LA17_0=='\'') ) {s = 25;}

                        else if ( (LA17_0=='/') ) {s = 26;}

                        else if ( ((LA17_0>='\t' && LA17_0<='\n')||LA17_0=='\r'||LA17_0==' ') ) {s = 27;}

                        else if ( ((LA17_0>='\u0000' && LA17_0<='\b')||(LA17_0>='\u000B' && LA17_0<='\f')||(LA17_0>='\u000E' && LA17_0<='\u001F')||LA17_0=='!'||(LA17_0>='#' && LA17_0<='&')||(LA17_0>='(' && LA17_0<='*')||(LA17_0>=';' && LA17_0<='@')||LA17_0=='\\'||LA17_0=='`'||(LA17_0>='~' && LA17_0<='\uFFFF')) ) {s = 28;}

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA17_24 = input.LA(1);

                        s = -1;
                        if ( ((LA17_24>='\u0000' && LA17_24<='\uFFFF')) ) {s = 54;}

                        else s = 28;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 17, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}