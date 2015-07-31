package de.cau.cs.kieler.kiml.grana.text.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalGranaLexer extends Lexer {
    public static final int RULE_BOOLEAN=4;
    public static final int RULE_STRING=5;
    public static final int RULE_SL_COMMENT=10;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__37=37;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__33=33;
    public static final int T__12=12;
    public static final int T__34=34;
    public static final int T__13=13;
    public static final int T__35=35;
    public static final int T__14=14;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_TFLOAT=6;
    public static final int RULE_ID=8;
    public static final int RULE_WS=11;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_NATURAL=7;
    public static final int RULE_ML_COMMENT=9;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators

    public InternalGranaLexer() {;} 
    public InternalGranaLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalGranaLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g"; }

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:11:7: ( 'globalResources' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:11:9: 'globalResources'
            {
            match("globalResources"); 


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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:12:7: ( 'globalOutputs' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:12:9: 'globalOutputs'
            {
            match("globalOutputs"); 


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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:13:7: ( 'execute' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:13:9: 'execute'
            {
            match("execute"); 


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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:14:7: ( 'job' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:14:9: 'job'
            {
            match("job"); 


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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:15:7: ( 'resources' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:15:9: 'resources'
            {
            match("resources"); 


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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:16:7: ( 'layoutoptions' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:16:9: 'layoutoptions'
            {
            match("layoutoptions"); 


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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:17:7: ( 'analyses' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:17:9: 'analyses'
            {
            match("analyses"); 


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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:18:7: ( 'output' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:18:9: 'output'
            {
            match("output"); 


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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:19:7: ( 'rangejob' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:19:9: 'rangejob'
            {
            match("rangejob"); 


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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:20:7: ( 'rangeoption' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:20:9: 'rangeoption'
            {
            match("rangeoption"); 


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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:21:7: ( 'rangeanalysis' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:21:9: 'rangeanalysis'
            {
            match("rangeanalysis"); 


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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:22:7: ( 'component' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:22:9: 'component'
            {
            match("component"); 


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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:23:7: ( 'floatvalues' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:23:9: 'floatvalues'
            {
            match("floatvalues"); 


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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:24:7: ( ',' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:24:9: ','
            {
            match(','); 

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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:25:7: ( 'intvalues' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:25:9: 'intvalues'
            {
            match("intvalues"); 


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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:26:7: ( 'intrange' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:26:9: 'intrange'
            {
            match("intrange"); 


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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:27:7: ( 'to' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:27:9: 'to'
            {
            match("to"); 


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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:28:7: ( 'ref' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:28:9: 'ref'
            {
            match("ref"); 


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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:29:7: ( 'filter' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:29:9: 'filter'
            {
            match("filter"); 


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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:30:7: ( '{' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:30:9: '{'
            {
            match('{'); 

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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:31:7: ( '}' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:31:9: '}'
            {
            match('}'); 

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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:32:7: ( ':' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:32:9: ':'
            {
            match(':'); 

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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:33:7: ( '.' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:33:9: '.'
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

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:34:7: ( 'all' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:34:9: 'all'
            {
            match("all"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:35:7: ( 'layoutBeforeAnalysis' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:35:9: 'layoutBeforeAnalysis'
            {
            match("layoutBeforeAnalysis"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:36:7: ( 'measureExecutionTime' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:36:9: 'measureExecutionTime'
            {
            match("measureExecutionTime"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "RULE_TFLOAT"
    public final void mRULE_TFLOAT() throws RecognitionException {
        try {
            int _type = RULE_TFLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:13: ( ( ( '+' | '-' ) ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? ) | ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:15: ( ( '+' | '-' ) ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? ) | ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:15: ( ( '+' | '-' ) ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? ) | ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            int alt24=4;
            alt24 = dfa24.predict(input);
            switch (alt24) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:16: ( '+' | '-' ) ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? )
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:26: ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? )
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
                            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:27: ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                            {
                            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:27: ( '0' .. '9' )+
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
                            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:28: '0' .. '9'
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

                            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:39: ( '.' ( '0' .. '9' )* )?
                            int alt3=2;
                            int LA3_0 = input.LA(1);

                            if ( (LA3_0=='.') ) {
                                alt3=1;
                            }
                            switch (alt3) {
                                case 1 :
                                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:40: '.' ( '0' .. '9' )*
                                    {
                                    match('.'); 
                                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:44: ( '0' .. '9' )*
                                    loop2:
                                    do {
                                        int alt2=2;
                                        int LA2_0 = input.LA(1);

                                        if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                                            alt2=1;
                                        }


                                        switch (alt2) {
                                    	case 1 :
                                    	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:45: '0' .. '9'
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

                            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:58: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                            int alt6=2;
                            int LA6_0 = input.LA(1);

                            if ( (LA6_0=='E'||LA6_0=='e') ) {
                                alt6=1;
                            }
                            switch (alt6) {
                                case 1 :
                                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:59: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
                                    {
                                    if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                                        input.consume();

                                    }
                                    else {
                                        MismatchedSetException mse = new MismatchedSetException(null,input);
                                        recover(mse);
                                        throw mse;}

                                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:69: ( '+' | '-' )?
                                    int alt4=2;
                                    int LA4_0 = input.LA(1);

                                    if ( (LA4_0=='+'||LA4_0=='-') ) {
                                        alt4=1;
                                    }
                                    switch (alt4) {
                                        case 1 :
                                            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:
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

                                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:80: ( '0' .. '9' )+
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
                                    	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:81: '0' .. '9'
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
                            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:94: '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                            {
                            match('.'); 
                            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:98: ( '0' .. '9' )+
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
                            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:99: '0' .. '9'
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

                            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:110: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                            int alt10=2;
                            int LA10_0 = input.LA(1);

                            if ( (LA10_0=='E'||LA10_0=='e') ) {
                                alt10=1;
                            }
                            switch (alt10) {
                                case 1 :
                                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:111: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
                                    {
                                    if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                                        input.consume();

                                    }
                                    else {
                                        MismatchedSetException mse = new MismatchedSetException(null,input);
                                        recover(mse);
                                        throw mse;}

                                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:121: ( '+' | '-' )?
                                    int alt8=2;
                                    int LA8_0 = input.LA(1);

                                    if ( (LA8_0=='+'||LA8_0=='-') ) {
                                        alt8=1;
                                    }
                                    switch (alt8) {
                                        case 1 :
                                            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:
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

                                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:132: ( '0' .. '9' )+
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
                                    	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:133: '0' .. '9'
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
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:147: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:147: ( '0' .. '9' )+
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
                    	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:148: '0' .. '9'
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
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:163: ( '0' .. '9' )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( ((LA13_0>='0' && LA13_0<='9')) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:164: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);

                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:175: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0=='E'||LA16_0=='e') ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:176: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
                            {
                            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}

                            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:186: ( '+' | '-' )?
                            int alt14=2;
                            int LA14_0 = input.LA(1);

                            if ( (LA14_0=='+'||LA14_0=='-') ) {
                                alt14=1;
                            }
                            switch (alt14) {
                                case 1 :
                                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:
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

                            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:197: ( '0' .. '9' )+
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
                            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:198: '0' .. '9'
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
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:211: '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                    {
                    match('.'); 
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:215: ( '0' .. '9' )+
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
                    	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:216: '0' .. '9'
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

                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:227: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0=='E'||LA20_0=='e') ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:228: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
                            {
                            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}

                            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:238: ( '+' | '-' )?
                            int alt18=2;
                            int LA18_0 = input.LA(1);

                            if ( (LA18_0=='+'||LA18_0=='-') ) {
                                alt18=1;
                            }
                            switch (alt18) {
                                case 1 :
                                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:
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

                            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:249: ( '0' .. '9' )+
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
                            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:250: '0' .. '9'
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
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:263: ( '0' .. '9' )+ ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:263: ( '0' .. '9' )+
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
                    	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:264: '0' .. '9'
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

                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:285: ( '+' | '-' )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0=='+'||LA22_0=='-') ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:
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

                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:296: ( '0' .. '9' )+
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
                    	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4142:297: '0' .. '9'
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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4144:14: ( ( '0' .. '9' )+ )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4144:16: ( '0' .. '9' )+
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4144:16: ( '0' .. '9' )+
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
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4144:17: '0' .. '9'
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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4146:14: ( ( 'true' | 'false' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4146:16: ( 'true' | 'false' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4146:16: ( 'true' | 'false' )
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
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4146:17: 'true'
                    {
                    match("true"); 


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4146:24: 'false'
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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4148:13: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4148:15: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
            {
            match('\"'); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4148:19: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
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
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4148:20: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
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
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4148:65: ~ ( ( '\\\\' | '\"' ) )
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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4150:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4150:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4150:11: ( '^' )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0=='^') ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4150:11: '^'
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

            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4150:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( ((LA29_0>='0' && LA29_0<='9')||(LA29_0>='A' && LA29_0<='Z')||LA29_0=='_'||(LA29_0>='a' && LA29_0<='z')) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:
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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4152:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4152:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4152:24: ( options {greedy=false; } : . )*
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
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4152:52: .
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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4154:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4154:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4154:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( ((LA31_0>='\u0000' && LA31_0<='\t')||(LA31_0>='\u000B' && LA31_0<='\f')||(LA31_0>='\u000E' && LA31_0<='\uFFFF')) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4154:24: ~ ( ( '\\n' | '\\r' ) )
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

            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4154:40: ( ( '\\r' )? '\\n' )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0=='\n'||LA33_0=='\r') ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4154:41: ( '\\r' )? '\\n'
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4154:41: ( '\\r' )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0=='\r') ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4154:41: '\\r'
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
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4156:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4156:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4156:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
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
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:
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
        // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:8: ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | RULE_TFLOAT | RULE_NATURAL | RULE_BOOLEAN | RULE_STRING | RULE_ID | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS )
        int alt35=34;
        alt35 = dfa35.predict(input);
        switch (alt35) {
            case 1 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:10: T__12
                {
                mT__12(); 

                }
                break;
            case 2 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:16: T__13
                {
                mT__13(); 

                }
                break;
            case 3 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:22: T__14
                {
                mT__14(); 

                }
                break;
            case 4 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:28: T__15
                {
                mT__15(); 

                }
                break;
            case 5 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:34: T__16
                {
                mT__16(); 

                }
                break;
            case 6 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:40: T__17
                {
                mT__17(); 

                }
                break;
            case 7 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:46: T__18
                {
                mT__18(); 

                }
                break;
            case 8 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:52: T__19
                {
                mT__19(); 

                }
                break;
            case 9 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:58: T__20
                {
                mT__20(); 

                }
                break;
            case 10 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:64: T__21
                {
                mT__21(); 

                }
                break;
            case 11 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:70: T__22
                {
                mT__22(); 

                }
                break;
            case 12 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:76: T__23
                {
                mT__23(); 

                }
                break;
            case 13 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:82: T__24
                {
                mT__24(); 

                }
                break;
            case 14 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:88: T__25
                {
                mT__25(); 

                }
                break;
            case 15 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:94: T__26
                {
                mT__26(); 

                }
                break;
            case 16 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:100: T__27
                {
                mT__27(); 

                }
                break;
            case 17 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:106: T__28
                {
                mT__28(); 

                }
                break;
            case 18 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:112: T__29
                {
                mT__29(); 

                }
                break;
            case 19 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:118: T__30
                {
                mT__30(); 

                }
                break;
            case 20 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:124: T__31
                {
                mT__31(); 

                }
                break;
            case 21 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:130: T__32
                {
                mT__32(); 

                }
                break;
            case 22 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:136: T__33
                {
                mT__33(); 

                }
                break;
            case 23 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:142: T__34
                {
                mT__34(); 

                }
                break;
            case 24 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:148: T__35
                {
                mT__35(); 

                }
                break;
            case 25 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:154: T__36
                {
                mT__36(); 

                }
                break;
            case 26 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:160: T__37
                {
                mT__37(); 

                }
                break;
            case 27 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:166: RULE_TFLOAT
                {
                mRULE_TFLOAT(); 

                }
                break;
            case 28 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:178: RULE_NATURAL
                {
                mRULE_NATURAL(); 

                }
                break;
            case 29 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:191: RULE_BOOLEAN
                {
                mRULE_BOOLEAN(); 

                }
                break;
            case 30 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:204: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 31 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:216: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 32 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:224: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 33 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:240: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 34 :
                // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1:256: RULE_WS
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
            return "4142:15: ( ( '+' | '-' ) ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? ) | ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )";
        }
    }
    static final String DFA35_eotS =
        "\1\uffff\11\25\1\uffff\2\25\3\uffff\1\50\1\25\1\uffff\1\52\4\uffff\16\25\1\74\1\25\1\uffff\1\25\3\uffff\2\25\1\101\1\25\1\103\3\25\1\107\6\25\1\uffff\4\25\1\uffff\1\25\1\uffff\3\25\1\uffff\7\25\1\136\13\25\1\136\2\25\1\uffff\11\25\1\171\2\25\1\174\5\25\1\u0082\7\25\1\uffff\2\25\1\uffff\5\25\1\uffff\1\25\1\u0092\4\25\1\u0097\3\25\1\u009b\3\25\1\u009f\1\uffff\4\25\1\uffff\1\u00a4\1\25\1\u00a6\1\uffff\3\25\1\uffff\4\25\1\uffff\1\25\1\uffff\3\25\1\u00b2\3\25\1\u00b6\3\25\1\uffff\3\25\1\uffff\2\25\1\u00bf\1\u00c0\1\u00c1\3\25\3\uffff\2\25\1\u00c7\2\25\1\uffff\10\25\1\u00d2\1\u00d3\2\uffff";
    static final String DFA35_eofS =
        "\u00d4\uffff";
    static final String DFA35_minS =
        "\1\11\1\154\1\170\1\157\2\141\1\154\1\165\1\157\1\141\1\uffff\1\156\1\157\3\uffff\1\60\1\145\1\uffff\1\56\2\uffff\1\52\1\uffff\1\157\1\145\1\142\1\146\1\156\1\171\1\141\1\154\1\164\1\155\1\157\2\154\1\164\1\60\1\165\1\uffff\1\141\3\uffff\1\142\1\143\1\60\1\157\1\60\1\147\1\157\1\154\1\60\2\160\1\141\1\164\1\163\1\162\1\uffff\1\145\1\163\1\141\1\165\1\uffff\1\165\1\uffff\1\145\1\165\1\171\1\uffff\1\165\1\157\1\164\2\145\2\141\1\60\1\165\1\154\1\164\1\162\1\141\1\164\1\163\1\164\1\156\1\166\1\162\1\60\1\154\1\156\1\uffff\1\162\1\117\1\145\1\143\1\157\1\160\1\156\1\102\1\145\1\60\1\145\1\141\1\60\1\165\1\147\2\145\1\165\1\60\1\145\1\142\1\164\1\141\1\160\1\145\1\163\1\uffff\1\156\1\154\1\uffff\2\145\1\105\1\163\1\164\1\uffff\1\163\1\60\1\151\1\154\1\164\1\146\1\60\1\164\1\165\1\163\1\60\1\170\1\157\1\160\1\60\1\uffff\1\157\1\171\1\151\1\157\1\uffff\1\60\1\145\1\60\1\uffff\1\145\2\165\1\uffff\1\156\1\163\1\157\1\162\1\uffff\1\163\1\uffff\1\143\1\162\1\164\1\60\1\151\1\156\1\145\1\60\1\165\1\143\1\163\1\uffff\2\163\1\101\1\uffff\1\164\1\145\3\60\1\156\1\151\1\163\3\uffff\1\141\1\157\1\60\1\154\1\156\1\uffff\1\171\1\124\1\163\2\151\1\155\1\163\1\145\2\60\2\uffff";
    static final String DFA35_maxS =
        "\1\175\1\154\1\170\1\157\1\145\1\141\1\156\1\165\1\157\1\154\1\uffff\1\156\1\162\3\uffff\1\71\1\145\1\uffff\1\145\2\uffff\1\57\1\uffff\1\157\1\145\1\142\1\163\1\156\1\171\1\141\1\154\1\164\1\155\1\157\2\154\1\164\1\172\1\165\1\uffff\1\141\3\uffff\1\142\1\143\1\172\1\157\1\172\1\147\1\157\1\154\1\172\2\160\1\141\1\164\1\163\1\166\1\uffff\1\145\1\163\1\141\1\165\1\uffff\1\165\1\uffff\1\145\1\165\1\171\1\uffff\1\165\1\157\1\164\2\145\2\141\1\172\1\165\1\154\1\164\1\162\1\157\1\164\1\163\1\164\1\156\1\166\1\162\1\172\1\154\1\156\1\uffff\1\162\1\122\1\145\1\143\1\157\1\160\1\156\1\157\1\145\1\172\1\145\1\141\1\172\1\165\1\147\2\145\1\165\1\172\1\145\1\142\1\164\1\141\1\160\1\145\1\163\1\uffff\1\156\1\154\1\uffff\2\145\1\105\1\163\1\164\1\uffff\1\163\1\172\1\151\1\154\1\164\1\146\1\172\1\164\1\165\1\163\1\172\1\170\1\157\1\160\1\172\1\uffff\1\157\1\171\1\151\1\157\1\uffff\1\172\1\145\1\172\1\uffff\1\145\2\165\1\uffff\1\156\1\163\1\157\1\162\1\uffff\1\163\1\uffff\1\143\1\162\1\164\1\172\1\151\1\156\1\145\1\172\1\165\1\143\1\163\1\uffff\2\163\1\101\1\uffff\1\164\1\145\3\172\1\156\1\151\1\163\3\uffff\1\141\1\157\1\172\1\154\1\156\1\uffff\1\171\1\124\1\163\2\151\1\155\1\163\1\145\2\172\2\uffff";
    static final String DFA35_acceptS =
        "\12\uffff\1\16\2\uffff\1\24\1\25\1\26\2\uffff\1\33\1\uffff\1\36\1\37\1\uffff\1\42\20\uffff\1\27\1\uffff\1\34\1\40\1\41\17\uffff\1\21\4\uffff\1\4\1\uffff\1\22\3\uffff\1\30\26\uffff\1\35\32\uffff\1\10\2\uffff\1\23\5\uffff\1\3\17\uffff\1\11\4\uffff\1\7\3\uffff\1\20\3\uffff\1\5\4\uffff\1\14\1\uffff\1\17\13\uffff\1\12\3\uffff\1\15\10\uffff\1\2\1\13\1\6\5\uffff\1\1\12\uffff\1\31\1\32";
    static final String DFA35_specialS =
        "\u00d4\uffff}>";
    static final String[] DFA35_transitionS = {
            "\2\27\2\uffff\1\27\22\uffff\1\27\1\uffff\1\24\10\uffff\1\22\1\12\1\22\1\20\1\26\12\23\1\17\6\uffff\32\25\3\uffff\2\25\1\uffff\1\6\1\25\1\10\1\25\1\2\1\11\1\1\1\25\1\13\1\3\1\25\1\5\1\21\1\25\1\7\2\25\1\4\1\25\1\14\6\25\1\15\1\uffff\1\16",
            "\1\30",
            "\1\31",
            "\1\32",
            "\1\34\3\uffff\1\33",
            "\1\35",
            "\1\37\1\uffff\1\36",
            "\1\40",
            "\1\41",
            "\1\44\7\uffff\1\43\2\uffff\1\42",
            "",
            "\1\45",
            "\1\46\2\uffff\1\47",
            "",
            "",
            "",
            "\12\22",
            "\1\51",
            "",
            "\1\22\1\uffff\12\23\13\uffff\1\22\37\uffff\1\22",
            "",
            "",
            "\1\53\4\uffff\1\54",
            "",
            "\1\55",
            "\1\56",
            "\1\57",
            "\1\61\14\uffff\1\60",
            "\1\62",
            "\1\63",
            "\1\64",
            "\1\65",
            "\1\66",
            "\1\67",
            "\1\70",
            "\1\71",
            "\1\72",
            "\1\73",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\75",
            "",
            "\1\76",
            "",
            "",
            "",
            "\1\77",
            "\1\100",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\102",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\104",
            "\1\105",
            "\1\106",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\110",
            "\1\111",
            "\1\112",
            "\1\113",
            "\1\114",
            "\1\116\3\uffff\1\115",
            "",
            "\1\117",
            "\1\120",
            "\1\121",
            "\1\122",
            "",
            "\1\123",
            "",
            "\1\124",
            "\1\125",
            "\1\126",
            "",
            "\1\127",
            "\1\130",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\134",
            "\1\135",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\137",
            "\1\140",
            "\1\141",
            "\1\142",
            "\1\145\10\uffff\1\143\4\uffff\1\144",
            "\1\146",
            "\1\147",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\153",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\154",
            "\1\155",
            "",
            "\1\156",
            "\1\160\2\uffff\1\157",
            "\1\161",
            "\1\162",
            "\1\163",
            "\1\164",
            "\1\165",
            "\1\167\54\uffff\1\166",
            "\1\170",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\172",
            "\1\173",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\175",
            "\1\176",
            "\1\177",
            "\1\u0080",
            "\1\u0081",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\u0083",
            "\1\u0084",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "\1\u0089",
            "",
            "\1\u008a",
            "\1\u008b",
            "",
            "\1\u008c",
            "\1\u008d",
            "\1\u008e",
            "\1\u008f",
            "\1\u0090",
            "",
            "\1\u0091",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\u0093",
            "\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\u0098",
            "\1\u0099",
            "\1\u009a",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\u009c",
            "\1\u009d",
            "\1\u009e",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "",
            "\1\u00a0",
            "\1\u00a1",
            "\1\u00a2",
            "\1\u00a3",
            "",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\u00a5",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "",
            "\1\u00a7",
            "\1\u00a8",
            "\1\u00a9",
            "",
            "\1\u00aa",
            "\1\u00ab",
            "\1\u00ac",
            "\1\u00ad",
            "",
            "\1\u00ae",
            "",
            "\1\u00af",
            "\1\u00b0",
            "\1\u00b1",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\u00b3",
            "\1\u00b4",
            "\1\u00b5",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\u00b7",
            "\1\u00b8",
            "\1\u00b9",
            "",
            "\1\u00ba",
            "\1\u00bb",
            "\1\u00bc",
            "",
            "\1\u00bd",
            "\1\u00be",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\u00c2",
            "\1\u00c3",
            "\1\u00c4",
            "",
            "",
            "",
            "\1\u00c5",
            "\1\u00c6",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\u00c8",
            "\1\u00c9",
            "",
            "\1\u00ca",
            "\1\u00cb",
            "\1\u00cc",
            "\1\u00cd",
            "\1\u00ce",
            "\1\u00cf",
            "\1\u00d0",
            "\1\u00d1",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "",
            ""
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
            return "1:1: Tokens : ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | RULE_TFLOAT | RULE_NATURAL | RULE_BOOLEAN | RULE_STRING | RULE_ID | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS );";
        }
    }
 

}