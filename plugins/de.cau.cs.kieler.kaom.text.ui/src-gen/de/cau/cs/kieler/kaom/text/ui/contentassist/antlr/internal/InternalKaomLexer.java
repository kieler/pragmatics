package de.cau.cs.kieler.kaom.text.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalKaomLexer extends Lexer {
    public static final int RULE_BOOLEAN=7;
    public static final int RULE_ID=5;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int RULE_ANY_OTHER=13;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int RULE_FLOAT=9;
    public static final int EOF=-1;
    public static final int RULE_SL_COMMENT=11;
    public static final int RULE_ML_COMMENT=10;
    public static final int T__19=19;
    public static final int RULE_STRING=4;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__14=14;
    public static final int RULE_INT=8;
    public static final int RULE_WS=12;
    public static final int RULE_COMMENT_ANNOTATION=6;

    // delegates
    // delegators

    public InternalKaomLexer() {;} 
    public InternalKaomLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalKaomLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g"; }

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:11:7: ( ';' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:11:9: ';'
            {
            match(';'); 

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
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:12:7: ( 'entity' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:12:9: 'entity'
            {
            match("entity"); 


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
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:13:7: ( '{' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:13:9: '{'
            {
            match('{'); 

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
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:14:7: ( '}' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:14:9: '}'
            {
            match('}'); 

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
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:15:7: ( 'link' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:15:9: 'link'
            {
            match("link"); 


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
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:16:7: ( 'to' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:16:9: 'to'
            {
            match("to"); 


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
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:17:7: ( 'port' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:17:9: 'port'
            {
            match("port"); 


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
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:18:7: ( 'relation' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:18:9: 'relation'
            {
            match("relation"); 


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
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:19:7: ( '@' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:19:9: '@'
            {
            match('@'); 

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
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:20:7: ( '(' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:20:9: '('
            {
            match('('); 

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
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:21:7: ( ')' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:21:9: ')'
            {
            match(')'); 

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
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:22:7: ( '[' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:22:9: '['
            {
            match('['); 

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
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:23:7: ( ']' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:23:9: ']'
            {
            match(']'); 

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
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:24:7: ( 'import' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:24:9: 'import'
            {
            match("import"); 


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
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:25:7: ( '.' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:25:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "RULE_COMMENT_ANNOTATION"
    public final void mRULE_COMMENT_ANNOTATION() throws RecognitionException {
        try {
            int _type = RULE_COMMENT_ANNOTATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4203:25: ( '/**' ( options {greedy=false; } : . )* '*/' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4203:27: '/**' ( options {greedy=false; } : . )* '*/'
            {
            match("/**"); 

            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4203:33: ( options {greedy=false; } : . )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='*') ) {
                    int LA1_1 = input.LA(2);

                    if ( (LA1_1=='/') ) {
                        alt1=2;
                    }
                    else if ( ((LA1_1>='\u0000' && LA1_1<='.')||(LA1_1>='0' && LA1_1<='\uFFFF')) ) {
                        alt1=1;
                    }


                }
                else if ( ((LA1_0>='\u0000' && LA1_0<=')')||(LA1_0>='+' && LA1_0<='\uFFFF')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4203:61: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop1;
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
    // $ANTLR end "RULE_COMMENT_ANNOTATION"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4205:17: ( '/*' ~ ( '*' ) ( options {greedy=false; } : . )* '*/' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4205:19: '/*' ~ ( '*' ) ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            if ( (input.LA(1)>='\u0000' && input.LA(1)<=')')||(input.LA(1)>='+' && input.LA(1)<='\uFFFF') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4205:31: ( options {greedy=false; } : . )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0=='*') ) {
                    int LA2_1 = input.LA(2);

                    if ( (LA2_1=='/') ) {
                        alt2=2;
                    }
                    else if ( ((LA2_1>='\u0000' && LA2_1<='.')||(LA2_1>='0' && LA2_1<='\uFFFF')) ) {
                        alt2=1;
                    }


                }
                else if ( ((LA2_0>='\u0000' && LA2_0<=')')||(LA2_0>='+' && LA2_0<='\uFFFF')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4205:59: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop2;
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

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4207:10: ( ( '-' )? ( '0' .. '9' )+ )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4207:12: ( '-' )? ( '0' .. '9' )+
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4207:12: ( '-' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='-') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4207:12: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4207:17: ( '0' .. '9' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4207:18: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_FLOAT"
    public final void mRULE_FLOAT() throws RecognitionException {
        try {
            int _type = RULE_FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4209:12: ( ( ( '-' )? ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? ( 'f' )? | ( '-' )? ( '0' .. '9' )+ 'f' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4209:14: ( ( '-' )? ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? ( 'f' )? | ( '-' )? ( '0' .. '9' )+ 'f' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4209:14: ( ( '-' )? ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? ( 'f' )? | ( '-' )? ( '0' .. '9' )+ 'f' )
            int alt14=2;
            alt14 = dfa14.predict(input);
            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4209:15: ( '-' )? ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? ( 'f' )?
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4209:15: ( '-' )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0=='-') ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4209:15: '-'
                            {
                            match('-'); 

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4209:20: ( '0' .. '9' )+
                    int cnt6=0;
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0>='0' && LA6_0<='9')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4209:21: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt6 >= 1 ) break loop6;
                                EarlyExitException eee =
                                    new EarlyExitException(6, input);
                                throw eee;
                        }
                        cnt6++;
                    } while (true);

                    match('.'); 
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4209:36: ( '0' .. '9' )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( ((LA7_0>='0' && LA7_0<='9')) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4209:37: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4209:48: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0=='E'||LA10_0=='e') ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4209:49: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
                            {
                            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}

                            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4209:59: ( '+' | '-' )?
                            int alt8=2;
                            int LA8_0 = input.LA(1);

                            if ( (LA8_0=='+'||LA8_0=='-') ) {
                                alt8=1;
                            }
                            switch (alt8) {
                                case 1 :
                                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:
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

                            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4209:70: ( '0' .. '9' )+
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
                            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4209:71: '0' .. '9'
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

                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4209:84: ( 'f' )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0=='f') ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4209:84: 'f'
                            {
                            match('f'); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4209:89: ( '-' )? ( '0' .. '9' )+ 'f'
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4209:89: ( '-' )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0=='-') ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4209:89: '-'
                            {
                            match('-'); 

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4209:94: ( '0' .. '9' )+
                    int cnt13=0;
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( ((LA13_0>='0' && LA13_0<='9')) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4209:95: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt13 >= 1 ) break loop13;
                                EarlyExitException eee =
                                    new EarlyExitException(13, input);
                                throw eee;
                        }
                        cnt13++;
                    } while (true);

                    match('f'); 

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

    // $ANTLR start "RULE_BOOLEAN"
    public final void mRULE_BOOLEAN() throws RecognitionException {
        try {
            int _type = RULE_BOOLEAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4211:14: ( ( 'true' | 'false' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4211:16: ( 'true' | 'false' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4211:16: ( 'true' | 'false' )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0=='t') ) {
                alt15=1;
            }
            else if ( (LA15_0=='f') ) {
                alt15=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4211:17: 'true'
                    {
                    match("true"); 


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4211:24: 'false'
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
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4213:13: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4213:15: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
            {
            match('\"'); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4213:19: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
            loop16:
            do {
                int alt16=3;
                int LA16_0 = input.LA(1);

                if ( (LA16_0=='\\') ) {
                    alt16=1;
                }
                else if ( ((LA16_0>='\u0000' && LA16_0<='!')||(LA16_0>='#' && LA16_0<='[')||(LA16_0>=']' && LA16_0<='\uFFFF')) ) {
                    alt16=2;
                }


                switch (alt16) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4213:20: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' )
            	    {
            	    match('\\'); 
            	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;
            	case 2 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4213:61: ~ ( ( '\\\\' | '\"' ) )
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
            	    break loop16;
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
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4215:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4215:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4215:11: ( '^' )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0=='^') ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4215:11: '^'
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

            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4215:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0>='0' && LA18_0<='9')||(LA18_0>='A' && LA18_0<='Z')||LA18_0=='_'||(LA18_0>='a' && LA18_0<='z')) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:
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
            	    break loop18;
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

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4217:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4217:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4217:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0>='\u0000' && LA19_0<='\t')||(LA19_0>='\u000B' && LA19_0<='\f')||(LA19_0>='\u000E' && LA19_0<='\uFFFF')) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4217:24: ~ ( ( '\\n' | '\\r' ) )
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
            	    break loop19;
                }
            } while (true);

            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4217:40: ( ( '\\r' )? '\\n' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0=='\n'||LA21_0=='\r') ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4217:41: ( '\\r' )? '\\n'
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4217:41: ( '\\r' )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0=='\r') ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4217:41: '\\r'
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
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4219:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4219:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4219:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt22=0;
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>='\t' && LA22_0<='\n')||LA22_0=='\r'||LA22_0==' ') ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:
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
            	    if ( cnt22 >= 1 ) break loop22;
                        EarlyExitException eee =
                            new EarlyExitException(22, input);
                        throw eee;
                }
                cnt22++;
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
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4221:16: ( . )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4221:18: .
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
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:8: ( T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | RULE_COMMENT_ANNOTATION | RULE_ML_COMMENT | RULE_INT | RULE_FLOAT | RULE_BOOLEAN | RULE_STRING | RULE_ID | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt23=25;
        alt23 = dfa23.predict(input);
        switch (alt23) {
            case 1 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:10: T__14
                {
                mT__14(); 

                }
                break;
            case 2 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:16: T__15
                {
                mT__15(); 

                }
                break;
            case 3 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:22: T__16
                {
                mT__16(); 

                }
                break;
            case 4 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:28: T__17
                {
                mT__17(); 

                }
                break;
            case 5 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:34: T__18
                {
                mT__18(); 

                }
                break;
            case 6 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:40: T__19
                {
                mT__19(); 

                }
                break;
            case 7 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:46: T__20
                {
                mT__20(); 

                }
                break;
            case 8 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:52: T__21
                {
                mT__21(); 

                }
                break;
            case 9 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:58: T__22
                {
                mT__22(); 

                }
                break;
            case 10 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:64: T__23
                {
                mT__23(); 

                }
                break;
            case 11 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:70: T__24
                {
                mT__24(); 

                }
                break;
            case 12 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:76: T__25
                {
                mT__25(); 

                }
                break;
            case 13 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:82: T__26
                {
                mT__26(); 

                }
                break;
            case 14 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:88: T__27
                {
                mT__27(); 

                }
                break;
            case 15 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:94: T__28
                {
                mT__28(); 

                }
                break;
            case 16 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:100: RULE_COMMENT_ANNOTATION
                {
                mRULE_COMMENT_ANNOTATION(); 

                }
                break;
            case 17 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:124: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 18 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:140: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 19 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:149: RULE_FLOAT
                {
                mRULE_FLOAT(); 

                }
                break;
            case 20 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:160: RULE_BOOLEAN
                {
                mRULE_BOOLEAN(); 

                }
                break;
            case 21 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:173: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 22 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:185: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 23 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:193: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 24 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:209: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 25 :
                // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1:217: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA14 dfa14 = new DFA14(this);
    protected DFA23 dfa23 = new DFA23(this);
    static final String DFA14_eotS =
        "\5\uffff";
    static final String DFA14_eofS =
        "\5\uffff";
    static final String DFA14_minS =
        "\1\55\1\60\1\56\2\uffff";
    static final String DFA14_maxS =
        "\2\71\1\146\2\uffff";
    static final String DFA14_acceptS =
        "\3\uffff\1\1\1\2";
    static final String DFA14_specialS =
        "\5\uffff}>";
    static final String[] DFA14_transitionS = {
            "\1\1\2\uffff\12\2",
            "\12\2",
            "\1\3\1\uffff\12\2\54\uffff\1\4",
            "",
            ""
    };

    static final short[] DFA14_eot = DFA.unpackEncodedString(DFA14_eotS);
    static final short[] DFA14_eof = DFA.unpackEncodedString(DFA14_eofS);
    static final char[] DFA14_min = DFA.unpackEncodedStringToUnsignedChars(DFA14_minS);
    static final char[] DFA14_max = DFA.unpackEncodedStringToUnsignedChars(DFA14_maxS);
    static final short[] DFA14_accept = DFA.unpackEncodedString(DFA14_acceptS);
    static final short[] DFA14_special = DFA.unpackEncodedString(DFA14_specialS);
    static final short[][] DFA14_transition;

    static {
        int numStates = DFA14_transitionS.length;
        DFA14_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA14_transition[i] = DFA.unpackEncodedString(DFA14_transitionS[i]);
        }
    }

    static class DFA14 extends DFA {

        public DFA14(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 14;
            this.eot = DFA14_eot;
            this.eof = DFA14_eof;
            this.min = DFA14_min;
            this.max = DFA14_max;
            this.accept = DFA14_accept;
            this.special = DFA14_special;
            this.transition = DFA14_transition;
        }
        public String getDescription() {
            return "4209:14: ( ( '-' )? ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? ( 'f' )? | ( '-' )? ( '0' .. '9' )+ 'f' )";
        }
    }
    static final String DFA23_eotS =
        "\2\uffff\1\33\2\uffff\4\33\5\uffff\1\33\1\uffff\2\30\1\55\1\33\2"+
        "\30\4\uffff\1\33\3\uffff\1\33\1\64\3\33\5\uffff\1\33\3\uffff\1\55"+
        "\2\uffff\1\33\2\uffff\2\33\1\uffff\4\33\2\uffff\2\33\1\104\1\105"+
        "\1\106\4\33\3\uffff\2\33\1\105\1\115\1\33\1\117\1\uffff\1\33\1\uffff"+
        "\1\121\1\uffff";
    static final String DFA23_eofS =
        "\122\uffff";
    static final String DFA23_minS =
        "\1\0\1\uffff\1\156\2\uffff\1\151\2\157\1\145\5\uffff\1\155\1\uffff"+
        "\1\52\1\60\1\56\1\141\1\0\1\101\4\uffff\1\164\3\uffff\1\156\1\60"+
        "\1\165\1\162\1\154\5\uffff\1\160\1\uffff\1\0\1\uffff\1\56\2\uffff"+
        "\1\154\2\uffff\1\151\1\153\1\uffff\1\145\1\164\1\141\1\157\2\uffff"+
        "\1\163\1\164\3\60\1\164\1\162\1\145\1\171\3\uffff\1\151\1\164\2"+
        "\60\1\157\1\60\1\uffff\1\156\1\uffff\1\60\1\uffff";
    static final String DFA23_maxS =
        "\1\uffff\1\uffff\1\156\2\uffff\1\151\1\162\1\157\1\145\5\uffff\1"+
        "\155\1\uffff\1\57\1\71\1\146\1\141\1\uffff\1\172\4\uffff\1\164\3"+
        "\uffff\1\156\1\172\1\165\1\162\1\154\5\uffff\1\160\1\uffff\1\uffff"+
        "\1\uffff\1\146\2\uffff\1\154\2\uffff\1\151\1\153\1\uffff\1\145\1"+
        "\164\1\141\1\157\2\uffff\1\163\1\164\3\172\1\164\1\162\1\145\1\171"+
        "\3\uffff\1\151\1\164\2\172\1\157\1\172\1\uffff\1\156\1\uffff\1\172"+
        "\1\uffff";
    static final String DFA23_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\1\4\4\uffff\1\11\1\12\1\13\1\14\1\15\1"+
        "\uffff\1\17\6\uffff\1\26\1\30\1\31\1\1\1\uffff\1\26\1\3\1\4\5\uffff"+
        "\1\11\1\12\1\13\1\14\1\15\1\uffff\1\17\1\uffff\1\27\1\uffff\1\22"+
        "\1\23\1\uffff\1\25\1\30\2\uffff\1\6\4\uffff\1\20\1\21\11\uffff\1"+
        "\5\1\24\1\7\6\uffff\1\2\1\uffff\1\16\1\uffff\1\10";
    static final String DFA23_specialS =
        "\1\2\23\uffff\1\1\25\uffff\1\0\47\uffff}>";
    static final String[] DFA23_transitionS = {
            "\11\30\2\27\2\30\1\27\22\30\1\27\1\30\1\24\5\30\1\12\1\13\3"+
            "\30\1\21\1\17\1\20\12\22\1\30\1\1\4\30\1\11\32\26\1\14\1\30"+
            "\1\15\1\25\1\26\1\30\4\26\1\2\1\23\2\26\1\16\2\26\1\5\3\26\1"+
            "\7\1\26\1\10\1\26\1\6\6\26\1\3\1\30\1\4\uff82\30",
            "",
            "\1\32",
            "",
            "",
            "\1\36",
            "\1\37\2\uffff\1\40",
            "\1\41",
            "\1\42",
            "",
            "",
            "",
            "",
            "",
            "\1\50",
            "",
            "\1\52\4\uffff\1\53",
            "\12\54",
            "\1\56\1\uffff\12\54\54\uffff\1\56",
            "\1\57",
            "\0\60",
            "\32\33\4\uffff\1\33\1\uffff\32\33",
            "",
            "",
            "",
            "",
            "\1\62",
            "",
            "",
            "",
            "\1\63",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\65",
            "\1\66",
            "\1\67",
            "",
            "",
            "",
            "",
            "",
            "\1\70",
            "",
            "\52\72\1\71\uffd5\72",
            "",
            "\1\56\1\uffff\12\54\54\uffff\1\56",
            "",
            "",
            "\1\73",
            "",
            "",
            "\1\74",
            "\1\75",
            "",
            "\1\76",
            "\1\77",
            "\1\100",
            "\1\101",
            "",
            "",
            "\1\102",
            "\1\103",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\107",
            "\1\110",
            "\1\111",
            "\1\112",
            "",
            "",
            "",
            "\1\113",
            "\1\114",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\116",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "",
            "\1\120",
            "",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            ""
    };

    static final short[] DFA23_eot = DFA.unpackEncodedString(DFA23_eotS);
    static final short[] DFA23_eof = DFA.unpackEncodedString(DFA23_eofS);
    static final char[] DFA23_min = DFA.unpackEncodedStringToUnsignedChars(DFA23_minS);
    static final char[] DFA23_max = DFA.unpackEncodedStringToUnsignedChars(DFA23_maxS);
    static final short[] DFA23_accept = DFA.unpackEncodedString(DFA23_acceptS);
    static final short[] DFA23_special = DFA.unpackEncodedString(DFA23_specialS);
    static final short[][] DFA23_transition;

    static {
        int numStates = DFA23_transitionS.length;
        DFA23_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA23_transition[i] = DFA.unpackEncodedString(DFA23_transitionS[i]);
        }
    }

    class DFA23 extends DFA {

        public DFA23(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 23;
            this.eot = DFA23_eot;
            this.eof = DFA23_eof;
            this.min = DFA23_min;
            this.max = DFA23_max;
            this.accept = DFA23_accept;
            this.special = DFA23_special;
            this.transition = DFA23_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | RULE_COMMENT_ANNOTATION | RULE_ML_COMMENT | RULE_INT | RULE_FLOAT | RULE_BOOLEAN | RULE_STRING | RULE_ID | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA23_42 = input.LA(1);

                        s = -1;
                        if ( (LA23_42=='*') ) {s = 57;}

                        else if ( ((LA23_42>='\u0000' && LA23_42<=')')||(LA23_42>='+' && LA23_42<='\uFFFF')) ) {s = 58;}

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA23_20 = input.LA(1);

                        s = -1;
                        if ( ((LA23_20>='\u0000' && LA23_20<='\uFFFF')) ) {s = 48;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA23_0 = input.LA(1);

                        s = -1;
                        if ( (LA23_0==';') ) {s = 1;}

                        else if ( (LA23_0=='e') ) {s = 2;}

                        else if ( (LA23_0=='{') ) {s = 3;}

                        else if ( (LA23_0=='}') ) {s = 4;}

                        else if ( (LA23_0=='l') ) {s = 5;}

                        else if ( (LA23_0=='t') ) {s = 6;}

                        else if ( (LA23_0=='p') ) {s = 7;}

                        else if ( (LA23_0=='r') ) {s = 8;}

                        else if ( (LA23_0=='@') ) {s = 9;}

                        else if ( (LA23_0=='(') ) {s = 10;}

                        else if ( (LA23_0==')') ) {s = 11;}

                        else if ( (LA23_0=='[') ) {s = 12;}

                        else if ( (LA23_0==']') ) {s = 13;}

                        else if ( (LA23_0=='i') ) {s = 14;}

                        else if ( (LA23_0=='.') ) {s = 15;}

                        else if ( (LA23_0=='/') ) {s = 16;}

                        else if ( (LA23_0=='-') ) {s = 17;}

                        else if ( ((LA23_0>='0' && LA23_0<='9')) ) {s = 18;}

                        else if ( (LA23_0=='f') ) {s = 19;}

                        else if ( (LA23_0=='\"') ) {s = 20;}

                        else if ( (LA23_0=='^') ) {s = 21;}

                        else if ( ((LA23_0>='A' && LA23_0<='Z')||LA23_0=='_'||(LA23_0>='a' && LA23_0<='d')||(LA23_0>='g' && LA23_0<='h')||(LA23_0>='j' && LA23_0<='k')||(LA23_0>='m' && LA23_0<='o')||LA23_0=='q'||LA23_0=='s'||(LA23_0>='u' && LA23_0<='z')) ) {s = 22;}

                        else if ( ((LA23_0>='\t' && LA23_0<='\n')||LA23_0=='\r'||LA23_0==' ') ) {s = 23;}

                        else if ( ((LA23_0>='\u0000' && LA23_0<='\b')||(LA23_0>='\u000B' && LA23_0<='\f')||(LA23_0>='\u000E' && LA23_0<='\u001F')||LA23_0=='!'||(LA23_0>='#' && LA23_0<='\'')||(LA23_0>='*' && LA23_0<=',')||LA23_0==':'||(LA23_0>='<' && LA23_0<='?')||LA23_0=='\\'||LA23_0=='`'||LA23_0=='|'||(LA23_0>='~' && LA23_0<='\uFFFF')) ) {s = 24;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 23, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}