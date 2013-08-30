package de.cau.cs.kieler.core.kgraph.text.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalKGraphLexer extends Lexer {
    public static final int RULE_ID=14;
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int RULE_PERCENT=10;
    public static final int RULE_BLUE=13;
    public static final int RULE_NATURAL=9;
    public static final int EOF=-1;
    public static final int T__93=93;
    public static final int T__94=94;
    public static final int T__19=19;
    public static final int T__91=91;
    public static final int T__92=92;
    public static final int T__90=90;
    public static final int T__99=99;
    public static final int T__98=98;
    public static final int T__97=97;
    public static final int T__96=96;
    public static final int T__95=95;
    public static final int T__137=137;
    public static final int T__136=136;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int T__85=85;
    public static final int T__84=84;
    public static final int T__87=87;
    public static final int T__86=86;
    public static final int T__89=89;
    public static final int T__88=88;
    public static final int RULE_ML_COMMENT=16;
    public static final int T__126=126;
    public static final int T__125=125;
    public static final int T__128=128;
    public static final int RULE_STRING=4;
    public static final int T__127=127;
    public static final int T__71=71;
    public static final int T__129=129;
    public static final int T__72=72;
    public static final int T__70=70;
    public static final int RULE_FSIZE=8;
    public static final int T__76=76;
    public static final int T__75=75;
    public static final int T__130=130;
    public static final int T__74=74;
    public static final int T__131=131;
    public static final int T__73=73;
    public static final int T__132=132;
    public static final int T__133=133;
    public static final int T__79=79;
    public static final int T__134=134;
    public static final int T__78=78;
    public static final int T__135=135;
    public static final int T__77=77;
    public static final int T__68=68;
    public static final int RULE_BOOLEAN=7;
    public static final int T__69=69;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__118=118;
    public static final int T__119=119;
    public static final int T__116=116;
    public static final int T__117=117;
    public static final int T__114=114;
    public static final int T__115=115;
    public static final int T__124=124;
    public static final int T__123=123;
    public static final int T__122=122;
    public static final int T__121=121;
    public static final int T__120=120;
    public static final int T__61=61;
    public static final int T__60=60;
    public static final int RULE_GREEN=12;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__107=107;
    public static final int T__108=108;
    public static final int T__109=109;
    public static final int T__103=103;
    public static final int T__59=59;
    public static final int T__104=104;
    public static final int T__105=105;
    public static final int T__106=106;
    public static final int T__111=111;
    public static final int T__110=110;
    public static final int T__113=113;
    public static final int T__112=112;
    public static final int T__50=50;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__46=46;
    public static final int RULE_RED=11;
    public static final int T__47=47;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__102=102;
    public static final int T__101=101;
    public static final int T__100=100;
    public static final int RULE_DEGREES=5;
    public static final int RULE_ALPHA=6;
    public static final int RULE_SL_COMMENT=17;
    public static final int RULE_TFLOAT=15;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int RULE_WS=18;

    // delegates
    // delegators

    public InternalKGraphLexer() {;} 
    public InternalKGraphLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalKGraphLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g"; }

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:11:7: ( 'kgraph' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:11:9: 'kgraph'
            {
            match("kgraph"); 


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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:12:7: ( 'knode' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:12:9: 'knode'
            {
            match("knode"); 


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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:13:7: ( '{' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:13:9: '{'
            {
            match('{'); 

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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:14:7: ( '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:14:9: '}'
            {
            match('}'); 

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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:15:7: ( 'kedge' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:15:9: 'kedge'
            {
            match("kedge"); 


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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:16:7: ( '(' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:16:9: '('
            {
            match('('); 

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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:17:7: ( ':' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:17:9: ':'
            {
            match(':'); 

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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:18:7: ( '->' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:18:9: '->'
            {
            match("->"); 


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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:19:7: ( ')' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:19:9: ')'
            {
            match(')'); 

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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:20:7: ( 'klabel' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:20:9: 'klabel'
            {
            match("klabel"); 


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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:21:7: ( 'kport' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:21:9: 'kport'
            {
            match("kport"); 


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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:22:7: ( '=' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:22:9: '='
            {
            match('='); 

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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:23:7: ( '[' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:23:9: '['
            {
            match('['); 

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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:24:7: ( ']' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:24:9: ']'
            {
            match(']'); 

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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:25:7: ( 'pos' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:25:9: 'pos'
            {
            match("pos"); 


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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:26:7: ( 'x' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:26:9: 'x'
            {
            match('x'); 

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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:27:7: ( 'y' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:27:9: 'y'
            {
            match('y'); 

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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:28:7: ( 'size' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:28:9: 'size'
            {
            match("size"); 


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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:29:7: ( 'width' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:29:9: 'width'
            {
            match("width"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:30:7: ( 'height' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:30:9: 'height'
            {
            match("height"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:31:7: ( 'properties' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:31:9: 'properties'
            {
            match("properties"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:32:7: ( 'insets' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:32:9: 'insets'
            {
            match("insets"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException {
        try {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:33:7: ( 'top' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:33:9: 'top'
            {
            match("top"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__41"

    // $ANTLR start "T__42"
    public final void mT__42() throws RecognitionException {
        try {
            int _type = T__42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:34:7: ( 'bottom' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:34:9: 'bottom'
            {
            match("bottom"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__42"

    // $ANTLR start "T__43"
    public final void mT__43() throws RecognitionException {
        try {
            int _type = T__43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:35:7: ( 'left' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:35:9: 'left'
            {
            match("left"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "T__44"
    public final void mT__44() throws RecognitionException {
        try {
            int _type = T__44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:36:7: ( 'right' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:36:9: 'right'
            {
            match("right"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__44"

    // $ANTLR start "T__45"
    public final void mT__45() throws RecognitionException {
        try {
            int _type = T__45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:37:7: ( 'points' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:37:9: 'points'
            {
            match("points"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__45"

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:38:7: ( ';' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:38:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:39:7: ( ',' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:39:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:40:7: ( 'styles' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:40:9: 'styles'
            {
            match("styles"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__48"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException {
        try {
            int _type = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:41:7: ( 'actions' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:41:9: 'actions'
            {
            match("actions"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__49"

    // $ANTLR start "T__50"
    public final void mT__50() throws RecognitionException {
        try {
            int _type = T__50;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:42:7: ( 'krendering' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:42:9: 'krendering'
            {
            match("krendering"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__50"

    // $ANTLR start "T__51"
    public final void mT__51() throws RecognitionException {
        try {
            int _type = T__51;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:43:7: ( '*' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:43:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__51"

    // $ANTLR start "T__52"
    public final void mT__52() throws RecognitionException {
        try {
            int _type = T__52;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:44:7: ( 'kchildArea' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:44:9: 'kchildArea'
            {
            match("kchildArea"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__52"

    // $ANTLR start "T__53"
    public final void mT__53() throws RecognitionException {
        try {
            int _type = T__53;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:45:7: ( 'ktext' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:45:9: 'ktext'
            {
            match("ktext"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__53"

    // $ANTLR start "T__54"
    public final void mT__54() throws RecognitionException {
        try {
            int _type = T__54;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:46:7: ( 'krectangle' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:46:9: 'krectangle'
            {
            match("krectangle"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__54"

    // $ANTLR start "T__55"
    public final void mT__55() throws RecognitionException {
        try {
            int _type = T__55;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:47:7: ( 'kroundedRectangle' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:47:9: 'kroundedRectangle'
            {
            match("kroundedRectangle"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__55"

    // $ANTLR start "T__56"
    public final void mT__56() throws RecognitionException {
        try {
            int _type = T__56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:48:7: ( 'kellipse' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:48:9: 'kellipse'
            {
            match("kellipse"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__56"

    // $ANTLR start "T__57"
    public final void mT__57() throws RecognitionException {
        try {
            int _type = T__57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:49:7: ( 'karc' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:49:9: 'karc'
            {
            match("karc"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__57"

    // $ANTLR start "T__58"
    public final void mT__58() throws RecognitionException {
        try {
            int _type = T__58;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:50:7: ( 'kcustomRendering' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:50:9: 'kcustomRendering'
            {
            match("kcustomRendering"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__58"

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException {
        try {
            int _type = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:51:7: ( 'kimage' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:51:9: 'kimage'
            {
            match("kimage"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__59"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException {
        try {
            int _type = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:52:7: ( 'junction' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:52:9: 'junction'
            {
            match("junction"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException {
        try {
            int _type = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:53:7: ( 'kpolyline' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:53:9: 'kpolyline'
            {
            match("kpolyline"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException {
        try {
            int _type = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:54:7: ( 'kpolygon' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:54:9: 'kpolygon'
            {
            match("kpolygon"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__62"

    // $ANTLR start "T__63"
    public final void mT__63() throws RecognitionException {
        try {
            int _type = T__63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:55:7: ( 'kroundedPolyline' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:55:9: 'kroundedPolyline'
            {
            match("kroundedPolyline"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__63"

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:56:7: ( 'kspline' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:56:9: 'kspline'
            {
            match("kspline"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__64"

    // $ANTLR start "T__65"
    public final void mT__65() throws RecognitionException {
        try {
            int _type = T__65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:57:7: ( 'modifier' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:57:9: 'modifier'
            {
            match("modifier"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__65"

    // $ANTLR start "T__66"
    public final void mT__66() throws RecognitionException {
        try {
            int _type = T__66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:58:7: ( 'null' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:58:9: 'null'
            {
            match("null"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__66"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:59:7: ( 'propagate' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:59:9: 'propagate'
            {
            match("propagate"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:60:7: ( 'foreground' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:60:9: 'foreground'
            {
            match("foreground"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:61:7: ( 'background' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:61:9: 'background'
            {
            match("background"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:62:7: ( 'bold' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:62:9: 'bold'
            {
            match("bold"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:63:7: ( 'italic' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:63:9: 'italic'
            {
            match("italic"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:64:7: ( 'fontName' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:64:9: 'fontName'
            {
            match("fontName"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:65:7: ( 'scale' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:65:9: 'scale'
            {
            match("scale"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:66:7: ( 'fontSize' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:66:9: 'fontSize'
            {
            match("fontSize"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:67:7: ( 'underline' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:67:9: 'underline'
            {
            match("underline"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:68:7: ( 'hAlign' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:68:9: 'hAlign'
            {
            match("hAlign"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:69:7: ( 'vAlign' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:69:9: 'vAlign'
            {
            match("vAlign"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:70:7: ( 'invisible' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:70:9: 'invisible'
            {
            match("invisible"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mT__79() throws RecognitionException {
        try {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:71:7: ( 'lineCap' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:71:9: 'lineCap'
            {
            match("lineCap"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:72:7: ( 'lineJoin' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:72:9: 'lineJoin'
            {
            match("lineJoin"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__80"

    // $ANTLR start "T__81"
    public final void mT__81() throws RecognitionException {
        try {
            int _type = T__81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:73:7: ( 'lineStyle' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:73:9: 'lineStyle'
            {
            match("lineStyle"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__81"

    // $ANTLR start "T__82"
    public final void mT__82() throws RecognitionException {
        try {
            int _type = T__82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:74:7: ( 'lineWidth' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:74:9: 'lineWidth'
            {
            match("lineWidth"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__82"

    // $ANTLR start "T__83"
    public final void mT__83() throws RecognitionException {
        try {
            int _type = T__83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:75:7: ( 'rotation' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:75:9: 'rotation'
            {
            match("rotation"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__83"

    // $ANTLR start "T__84"
    public final void mT__84() throws RecognitionException {
        try {
            int _type = T__84;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:76:7: ( 'anchor' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:76:9: 'anchor'
            {
            match("anchor"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__84"

    // $ANTLR start "T__85"
    public final void mT__85() throws RecognitionException {
        try {
            int _type = T__85;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:77:7: ( 'shadow' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:77:9: 'shadow'
            {
            match("shadow"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__85"

    // $ANTLR start "T__86"
    public final void mT__86() throws RecognitionException {
        try {
            int _type = T__86;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:78:7: ( 'reference' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:78:9: 'reference'
            {
            match("reference"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__86"

    // $ANTLR start "T__87"
    public final void mT__87() throws RecognitionException {
        try {
            int _type = T__87;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:79:7: ( 'krenderingLibrary' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:79:9: 'krenderingLibrary'
            {
            match("krenderingLibrary"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__87"

    // $ANTLR start "T__88"
    public final void mT__88() throws RecognitionException {
        try {
            int _type = T__88;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:80:7: ( 'kstylesTemplate' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:80:9: 'kstylesTemplate'
            {
            match("kstylesTemplate"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__88"

    // $ANTLR start "T__89"
    public final void mT__89() throws RecognitionException {
        try {
            int _type = T__89;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:81:7: ( 'grid' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:81:9: 'grid'
            {
            match("grid"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__89"

    // $ANTLR start "T__90"
    public final void mT__90() throws RecognitionException {
        try {
            int _type = T__90;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:82:7: ( 'topLeftAnchor' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:82:9: 'topLeftAnchor'
            {
            match("topLeftAnchor"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__90"

    // $ANTLR start "T__91"
    public final void mT__91() throws RecognitionException {
        try {
            int _type = T__91;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:83:7: ( 'bottomRightAnchor' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:83:9: 'bottomRightAnchor'
            {
            match("bottomRightAnchor"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__91"

    // $ANTLR start "T__92"
    public final void mT__92() throws RecognitionException {
        try {
            int _type = T__92;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:84:7: ( 'columns' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:84:9: 'columns'
            {
            match("columns"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__92"

    // $ANTLR start "T__93"
    public final void mT__93() throws RecognitionException {
        try {
            int _type = T__93;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:85:7: ( 'areaData' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:85:9: 'areaData'
            {
            match("areaData"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__93"

    // $ANTLR start "T__94"
    public final void mT__94() throws RecognitionException {
        try {
            int _type = T__94;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:86:7: ( 'pointData' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:86:9: 'pointData'
            {
            match("pointData"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__94"

    // $ANTLR start "T__95"
    public final void mT__95() throws RecognitionException {
        try {
            int _type = T__95;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:87:7: ( 'referencePoint' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:87:9: 'referencePoint'
            {
            match("referencePoint"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__95"

    // $ANTLR start "T__96"
    public final void mT__96() throws RecognitionException {
        try {
            int _type = T__96;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:88:7: ( 'minimalWidth' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:88:9: 'minimalWidth'
            {
            match("minimalWidth"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__96"

    // $ANTLR start "T__97"
    public final void mT__97() throws RecognitionException {
        try {
            int _type = T__97;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:89:7: ( 'minimalHeight' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:89:9: 'minimalHeight'
            {
            match("minimalHeight"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__97"

    // $ANTLR start "T__98"
    public final void mT__98() throws RecognitionException {
        try {
            int _type = T__98;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:90:7: ( 'horizontalAlignment' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:90:9: 'horizontalAlignment'
            {
            match("horizontalAlignment"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__98"

    // $ANTLR start "T__99"
    public final void mT__99() throws RecognitionException {
        try {
            int _type = T__99;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:91:7: ( 'verticalAlignment' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:91:9: 'verticalAlignment'
            {
            match("verticalAlignment"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__99"

    // $ANTLR start "T__100"
    public final void mT__100() throws RecognitionException {
        try {
            int _type = T__100;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:92:8: ( 'horizontalMargin' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:92:10: 'horizontalMargin'
            {
            match("horizontalMargin"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__100"

    // $ANTLR start "T__101"
    public final void mT__101() throws RecognitionException {
        try {
            int _type = T__101;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:93:8: ( 'verticalMargin' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:93:10: 'verticalMargin'
            {
            match("verticalMargin"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__101"

    // $ANTLR start "T__102"
    public final void mT__102() throws RecognitionException {
        try {
            int _type = T__102;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:94:8: ( 'gridData' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:94:10: 'gridData'
            {
            match("gridData"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__102"

    // $ANTLR start "T__103"
    public final void mT__103() throws RecognitionException {
        try {
            int _type = T__103;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:95:8: ( 'minCellWidth' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:95:10: 'minCellWidth'
            {
            match("minCellWidth"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__103"

    // $ANTLR start "T__104"
    public final void mT__104() throws RecognitionException {
        try {
            int _type = T__104;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:96:8: ( 'minCellHeight' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:96:10: 'minCellHeight'
            {
            match("minCellHeight"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__104"

    // $ANTLR start "T__105"
    public final void mT__105() throws RecognitionException {
        try {
            int _type = T__105;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:97:8: ( 'maxCellWidth' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:97:10: 'maxCellWidth'
            {
            match("maxCellWidth"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__105"

    // $ANTLR start "T__106"
    public final void mT__106() throws RecognitionException {
        try {
            int _type = T__106;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:98:8: ( 'maxCellHeight' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:98:10: 'maxCellHeight'
            {
            match("maxCellHeight"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__106"

    // $ANTLR start "T__107"
    public final void mT__107() throws RecognitionException {
        try {
            int _type = T__107;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:99:8: ( 'decoratorData' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:99:10: 'decoratorData'
            {
            match("decoratorData"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__107"

    // $ANTLR start "T__108"
    public final void mT__108() throws RecognitionException {
        try {
            int _type = T__108;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:100:8: ( 'xoffset' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:100:10: 'xoffset'
            {
            match("xoffset"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__108"

    // $ANTLR start "T__109"
    public final void mT__109() throws RecognitionException {
        try {
            int _type = T__109;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:101:8: ( 'yoffset' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:101:10: 'yoffset'
            {
            match("yoffset"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__109"

    // $ANTLR start "T__110"
    public final void mT__110() throws RecognitionException {
        try {
            int _type = T__110;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:102:8: ( 'relativePos' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:102:10: 'relativePos'
            {
            match("relativePos"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__110"

    // $ANTLR start "T__111"
    public final void mT__111() throws RecognitionException {
        try {
            int _type = T__111;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:103:8: ( 'absolutePos' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:103:10: 'absolutePos'
            {
            match("absolutePos"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__111"

    // $ANTLR start "T__112"
    public final void mT__112() throws RecognitionException {
        try {
            int _type = T__112;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:104:8: ( 'rotateWithLine' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:104:10: 'rotateWithLine'
            {
            match("rotateWithLine"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__112"

    // $ANTLR start "T__113"
    public final void mT__113() throws RecognitionException {
        try {
            int _type = T__113;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:105:8: ( '=>' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:105:10: '=>'
            {
            match("=>"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__113"

    // $ANTLR start "T__114"
    public final void mT__114() throws RecognitionException {
        try {
            int _type = T__114;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:106:8: ( '+' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:106:10: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__114"

    // $ANTLR start "T__115"
    public final void mT__115() throws RecognitionException {
        try {
            int _type = T__115;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:107:8: ( '.' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:107:10: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__115"

    // $ANTLR start "T__116"
    public final void mT__116() throws RecognitionException {
        try {
            int _type = T__116;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:108:8: ( 'open' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:108:10: 'open'
            {
            match("open"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__116"

    // $ANTLR start "T__117"
    public final void mT__117() throws RecognitionException {
        try {
            int _type = T__117;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:109:8: ( 'chord' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:109:10: 'chord'
            {
            match("chord"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__117"

    // $ANTLR start "T__118"
    public final void mT__118() throws RecognitionException {
        try {
            int _type = T__118;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:110:8: ( 'pie' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:110:10: 'pie'
            {
            match("pie"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__118"

    // $ANTLR start "T__119"
    public final void mT__119() throws RecognitionException {
        try {
            int _type = T__119;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:111:8: ( 'center' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:111:10: 'center'
            {
            match("center"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__119"

    // $ANTLR start "T__120"
    public final void mT__120() throws RecognitionException {
        try {
            int _type = T__120;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:112:8: ( 'none' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:112:10: 'none'
            {
            match("none"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__120"

    // $ANTLR start "T__121"
    public final void mT__121() throws RecognitionException {
        try {
            int _type = T__121;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:113:8: ( 'single' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:113:10: 'single'
            {
            match("single"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__121"

    // $ANTLR start "T__122"
    public final void mT__122() throws RecognitionException {
        try {
            int _type = T__122;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:114:8: ( 'double' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:114:10: 'double'
            {
            match("double"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__122"

    // $ANTLR start "T__123"
    public final void mT__123() throws RecognitionException {
        try {
            int _type = T__123;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:115:8: ( 'error' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:115:10: 'error'
            {
            match("error"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__123"

    // $ANTLR start "T__124"
    public final void mT__124() throws RecognitionException {
        try {
            int _type = T__124;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:116:8: ( 'squiggle' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:116:10: 'squiggle'
            {
            match("squiggle"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__124"

    // $ANTLR start "T__125"
    public final void mT__125() throws RecognitionException {
        try {
            int _type = T__125;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:117:8: ( 'link' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:117:10: 'link'
            {
            match("link"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__125"

    // $ANTLR start "T__126"
    public final void mT__126() throws RecognitionException {
        try {
            int _type = T__126;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:118:8: ( 'solid' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:118:10: 'solid'
            {
            match("solid"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__126"

    // $ANTLR start "T__127"
    public final void mT__127() throws RecognitionException {
        try {
            int _type = T__127;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:119:8: ( 'dash' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:119:10: 'dash'
            {
            match("dash"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__127"

    // $ANTLR start "T__128"
    public final void mT__128() throws RecognitionException {
        try {
            int _type = T__128;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:120:8: ( 'dot' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:120:10: 'dot'
            {
            match("dot"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__128"

    // $ANTLR start "T__129"
    public final void mT__129() throws RecognitionException {
        try {
            int _type = T__129;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:121:8: ( 'dash-dot' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:121:10: 'dash-dot'
            {
            match("dash-dot"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__129"

    // $ANTLR start "T__130"
    public final void mT__130() throws RecognitionException {
        try {
            int _type = T__130;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:122:8: ( 'dash-dot-dot' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:122:10: 'dash-dot-dot'
            {
            match("dash-dot-dot"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__130"

    // $ANTLR start "T__131"
    public final void mT__131() throws RecognitionException {
        try {
            int _type = T__131;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:123:8: ( 'flat' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:123:10: 'flat'
            {
            match("flat"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__131"

    // $ANTLR start "T__132"
    public final void mT__132() throws RecognitionException {
        try {
            int _type = T__132;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:124:8: ( 'round' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:124:10: 'round'
            {
            match("round"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__132"

    // $ANTLR start "T__133"
    public final void mT__133() throws RecognitionException {
        try {
            int _type = T__133;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:125:8: ( 'square' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:125:10: 'square'
            {
            match("square"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__133"

    // $ANTLR start "T__134"
    public final void mT__134() throws RecognitionException {
        try {
            int _type = T__134;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:126:8: ( 'miter' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:126:10: 'miter'
            {
            match("miter"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__134"

    // $ANTLR start "T__135"
    public final void mT__135() throws RecognitionException {
        try {
            int _type = T__135;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:127:8: ( 'bevel' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:127:10: 'bevel'
            {
            match("bevel"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__135"

    // $ANTLR start "T__136"
    public final void mT__136() throws RecognitionException {
        try {
            int _type = T__136;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:128:8: ( 'singleClick' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:128:10: 'singleClick'
            {
            match("singleClick"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__136"

    // $ANTLR start "T__137"
    public final void mT__137() throws RecognitionException {
        try {
            int _type = T__137;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:129:8: ( 'doubleClick' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:129:10: 'doubleClick'
            {
            match("doubleClick"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__137"

    // $ANTLR start "RULE_BOOLEAN"
    public final void mRULE_BOOLEAN() throws RecognitionException {
        try {
            int _type = RULE_BOOLEAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7855:14: ( ( 'true' | 'false' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7855:16: ( 'true' | 'false' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7855:16: ( 'true' | 'false' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='t') ) {
                alt1=1;
            }
            else if ( (LA1_0=='f') ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7855:17: 'true'
                    {
                    match("true"); 


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7855:24: 'false'
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

    // $ANTLR start "RULE_RED"
    public final void mRULE_RED() throws RecognitionException {
        try {
            int _type = RULE_RED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7857:10: ( ( '0' .. '9' )+ 'r' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7857:12: ( '0' .. '9' )+ 'r'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7857:12: ( '0' .. '9' )+
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
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7857:13: '0' .. '9'
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

            match('r'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_RED"

    // $ANTLR start "RULE_GREEN"
    public final void mRULE_GREEN() throws RecognitionException {
        try {
            int _type = RULE_GREEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7859:12: ( ( '0' .. '9' )+ 'g' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7859:14: ( '0' .. '9' )+ 'g'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7859:14: ( '0' .. '9' )+
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
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7859:15: '0' .. '9'
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

            match('g'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_GREEN"

    // $ANTLR start "RULE_BLUE"
    public final void mRULE_BLUE() throws RecognitionException {
        try {
            int _type = RULE_BLUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7861:11: ( ( '0' .. '9' )+ 'b' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7861:13: ( '0' .. '9' )+ 'b'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7861:13: ( '0' .. '9' )+
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
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7861:14: '0' .. '9'
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

            match('b'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_BLUE"

    // $ANTLR start "RULE_ALPHA"
    public final void mRULE_ALPHA() throws RecognitionException {
        try {
            int _type = RULE_ALPHA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7863:12: ( ( '0' .. '9' )+ 'a' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7863:14: ( '0' .. '9' )+ 'a'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7863:14: ( '0' .. '9' )+
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
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7863:15: '0' .. '9'
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

            match('a'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ALPHA"

    // $ANTLR start "RULE_FSIZE"
    public final void mRULE_FSIZE() throws RecognitionException {
        try {
            int _type = RULE_FSIZE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7865:12: ( ( '0' .. '9' )+ 'pt' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7865:14: ( '0' .. '9' )+ 'pt'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7865:14: ( '0' .. '9' )+
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
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7865:15: '0' .. '9'
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

            match("pt"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_FSIZE"

    // $ANTLR start "RULE_DEGREES"
    public final void mRULE_DEGREES() throws RecognitionException {
        try {
            int _type = RULE_DEGREES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7867:14: ( ( '+' | '-' )? ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? | '.' ( '0' .. '9' )+ ) 'deg' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7867:16: ( '+' | '-' )? ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? | '.' ( '0' .. '9' )+ ) 'deg'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7867:16: ( '+' | '-' )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='+'||LA7_0=='-') ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7867:27: ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? | '.' ( '0' .. '9' )+ )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>='0' && LA12_0<='9')) ) {
                alt12=1;
            }
            else if ( (LA12_0=='.') ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7867:28: ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )?
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7867:28: ( '0' .. '9' )+
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
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7867:29: '0' .. '9'
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7867:40: ( '.' ( '0' .. '9' )* )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0=='.') ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7867:41: '.' ( '0' .. '9' )*
                            {
                            match('.'); 
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7867:45: ( '0' .. '9' )*
                            loop9:
                            do {
                                int alt9=2;
                                int LA9_0 = input.LA(1);

                                if ( ((LA9_0>='0' && LA9_0<='9')) ) {
                                    alt9=1;
                                }


                                switch (alt9) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7867:46: '0' .. '9'
                            	    {
                            	    matchRange('0','9'); 

                            	    }
                            	    break;

                            	default :
                            	    break loop9;
                                }
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7867:59: '.' ( '0' .. '9' )+
                    {
                    match('.'); 
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7867:63: ( '0' .. '9' )+
                    int cnt11=0;
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( ((LA11_0>='0' && LA11_0<='9')) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7867:64: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

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
                    break;

            }

            match("deg"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_DEGREES"

    // $ANTLR start "RULE_PERCENT"
    public final void mRULE_PERCENT() throws RecognitionException {
        try {
            int _type = RULE_PERCENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7869:14: ( ( '+' | '-' )? ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? | '.' ( '0' .. '9' )+ ) '%' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7869:16: ( '+' | '-' )? ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? | '.' ( '0' .. '9' )+ ) '%'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7869:16: ( '+' | '-' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0=='+'||LA13_0=='-') ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7869:27: ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? | '.' ( '0' .. '9' )+ )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0>='0' && LA18_0<='9')) ) {
                alt18=1;
            }
            else if ( (LA18_0=='.') ) {
                alt18=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7869:28: ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )?
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7869:28: ( '0' .. '9' )+
                    int cnt14=0;
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( ((LA14_0>='0' && LA14_0<='9')) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7869:29: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt14 >= 1 ) break loop14;
                                EarlyExitException eee =
                                    new EarlyExitException(14, input);
                                throw eee;
                        }
                        cnt14++;
                    } while (true);

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7869:40: ( '.' ( '0' .. '9' )* )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0=='.') ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7869:41: '.' ( '0' .. '9' )*
                            {
                            match('.'); 
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7869:45: ( '0' .. '9' )*
                            loop15:
                            do {
                                int alt15=2;
                                int LA15_0 = input.LA(1);

                                if ( ((LA15_0>='0' && LA15_0<='9')) ) {
                                    alt15=1;
                                }


                                switch (alt15) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7869:46: '0' .. '9'
                            	    {
                            	    matchRange('0','9'); 

                            	    }
                            	    break;

                            	default :
                            	    break loop15;
                                }
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7869:59: '.' ( '0' .. '9' )+
                    {
                    match('.'); 
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7869:63: ( '0' .. '9' )+
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
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7869:64: '0' .. '9'
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


                    }
                    break;

            }

            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_PERCENT"

    // $ANTLR start "RULE_TFLOAT"
    public final void mRULE_TFLOAT() throws RecognitionException {
        try {
            int _type = RULE_TFLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:13: ( ( ( '+' | '-' ) ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? ) | ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:15: ( ( '+' | '-' ) ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? ) | ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:15: ( ( '+' | '-' ) ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? ) | ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            int alt42=4;
            alt42 = dfa42.predict(input);
            switch (alt42) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:16: ( '+' | '-' ) ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? )
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:26: ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? )
                    int alt29=2;
                    int LA29_0 = input.LA(1);

                    if ( ((LA29_0>='0' && LA29_0<='9')) ) {
                        alt29=1;
                    }
                    else if ( (LA29_0=='.') ) {
                        alt29=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 29, 0, input);

                        throw nvae;
                    }
                    switch (alt29) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:27: ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:27: ( '0' .. '9' )+
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
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:28: '0' .. '9'
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:39: ( '.' ( '0' .. '9' )* )?
                            int alt21=2;
                            int LA21_0 = input.LA(1);

                            if ( (LA21_0=='.') ) {
                                alt21=1;
                            }
                            switch (alt21) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:40: '.' ( '0' .. '9' )*
                                    {
                                    match('.'); 
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:44: ( '0' .. '9' )*
                                    loop20:
                                    do {
                                        int alt20=2;
                                        int LA20_0 = input.LA(1);

                                        if ( ((LA20_0>='0' && LA20_0<='9')) ) {
                                            alt20=1;
                                        }


                                        switch (alt20) {
                                    	case 1 :
                                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:45: '0' .. '9'
                                    	    {
                                    	    matchRange('0','9'); 

                                    	    }
                                    	    break;

                                    	default :
                                    	    break loop20;
                                        }
                                    } while (true);


                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:58: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                            int alt24=2;
                            int LA24_0 = input.LA(1);

                            if ( (LA24_0=='E'||LA24_0=='e') ) {
                                alt24=1;
                            }
                            switch (alt24) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:59: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
                                    {
                                    if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                                        input.consume();

                                    }
                                    else {
                                        MismatchedSetException mse = new MismatchedSetException(null,input);
                                        recover(mse);
                                        throw mse;}

                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:69: ( '+' | '-' )?
                                    int alt22=2;
                                    int LA22_0 = input.LA(1);

                                    if ( (LA22_0=='+'||LA22_0=='-') ) {
                                        alt22=1;
                                    }
                                    switch (alt22) {
                                        case 1 :
                                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:
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

                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:80: ( '0' .. '9' )+
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
                                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:81: '0' .. '9'
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
                            break;
                        case 2 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:94: '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                            {
                            match('.'); 
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:98: ( '0' .. '9' )+
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
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:99: '0' .. '9'
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:110: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                            int alt28=2;
                            int LA28_0 = input.LA(1);

                            if ( (LA28_0=='E'||LA28_0=='e') ) {
                                alt28=1;
                            }
                            switch (alt28) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:111: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
                                    {
                                    if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                                        input.consume();

                                    }
                                    else {
                                        MismatchedSetException mse = new MismatchedSetException(null,input);
                                        recover(mse);
                                        throw mse;}

                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:121: ( '+' | '-' )?
                                    int alt26=2;
                                    int LA26_0 = input.LA(1);

                                    if ( (LA26_0=='+'||LA26_0=='-') ) {
                                        alt26=1;
                                    }
                                    switch (alt26) {
                                        case 1 :
                                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:
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

                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:132: ( '0' .. '9' )+
                                    int cnt27=0;
                                    loop27:
                                    do {
                                        int alt27=2;
                                        int LA27_0 = input.LA(1);

                                        if ( ((LA27_0>='0' && LA27_0<='9')) ) {
                                            alt27=1;
                                        }


                                        switch (alt27) {
                                    	case 1 :
                                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:133: '0' .. '9'
                                    	    {
                                    	    matchRange('0','9'); 

                                    	    }
                                    	    break;

                                    	default :
                                    	    if ( cnt27 >= 1 ) break loop27;
                                                EarlyExitException eee =
                                                    new EarlyExitException(27, input);
                                                throw eee;
                                        }
                                        cnt27++;
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:147: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:147: ( '0' .. '9' )+
                    int cnt30=0;
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( ((LA30_0>='0' && LA30_0<='9')) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:148: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt30 >= 1 ) break loop30;
                                EarlyExitException eee =
                                    new EarlyExitException(30, input);
                                throw eee;
                        }
                        cnt30++;
                    } while (true);

                    match('.'); 
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:163: ( '0' .. '9' )*
                    loop31:
                    do {
                        int alt31=2;
                        int LA31_0 = input.LA(1);

                        if ( ((LA31_0>='0' && LA31_0<='9')) ) {
                            alt31=1;
                        }


                        switch (alt31) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:164: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop31;
                        }
                    } while (true);

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:175: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0=='E'||LA34_0=='e') ) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:176: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
                            {
                            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:186: ( '+' | '-' )?
                            int alt32=2;
                            int LA32_0 = input.LA(1);

                            if ( (LA32_0=='+'||LA32_0=='-') ) {
                                alt32=1;
                            }
                            switch (alt32) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:197: ( '0' .. '9' )+
                            int cnt33=0;
                            loop33:
                            do {
                                int alt33=2;
                                int LA33_0 = input.LA(1);

                                if ( ((LA33_0>='0' && LA33_0<='9')) ) {
                                    alt33=1;
                                }


                                switch (alt33) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:198: '0' .. '9'
                            	    {
                            	    matchRange('0','9'); 

                            	    }
                            	    break;

                            	default :
                            	    if ( cnt33 >= 1 ) break loop33;
                                        EarlyExitException eee =
                                            new EarlyExitException(33, input);
                                        throw eee;
                                }
                                cnt33++;
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:211: '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                    {
                    match('.'); 
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:215: ( '0' .. '9' )+
                    int cnt35=0;
                    loop35:
                    do {
                        int alt35=2;
                        int LA35_0 = input.LA(1);

                        if ( ((LA35_0>='0' && LA35_0<='9')) ) {
                            alt35=1;
                        }


                        switch (alt35) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:216: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt35 >= 1 ) break loop35;
                                EarlyExitException eee =
                                    new EarlyExitException(35, input);
                                throw eee;
                        }
                        cnt35++;
                    } while (true);

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:227: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                    int alt38=2;
                    int LA38_0 = input.LA(1);

                    if ( (LA38_0=='E'||LA38_0=='e') ) {
                        alt38=1;
                    }
                    switch (alt38) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:228: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
                            {
                            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:238: ( '+' | '-' )?
                            int alt36=2;
                            int LA36_0 = input.LA(1);

                            if ( (LA36_0=='+'||LA36_0=='-') ) {
                                alt36=1;
                            }
                            switch (alt36) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:249: ( '0' .. '9' )+
                            int cnt37=0;
                            loop37:
                            do {
                                int alt37=2;
                                int LA37_0 = input.LA(1);

                                if ( ((LA37_0>='0' && LA37_0<='9')) ) {
                                    alt37=1;
                                }


                                switch (alt37) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:250: '0' .. '9'
                            	    {
                            	    matchRange('0','9'); 

                            	    }
                            	    break;

                            	default :
                            	    if ( cnt37 >= 1 ) break loop37;
                                        EarlyExitException eee =
                                            new EarlyExitException(37, input);
                                        throw eee;
                                }
                                cnt37++;
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:263: ( '0' .. '9' )+ ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:263: ( '0' .. '9' )+
                    int cnt39=0;
                    loop39:
                    do {
                        int alt39=2;
                        int LA39_0 = input.LA(1);

                        if ( ((LA39_0>='0' && LA39_0<='9')) ) {
                            alt39=1;
                        }


                        switch (alt39) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:264: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt39 >= 1 ) break loop39;
                                EarlyExitException eee =
                                    new EarlyExitException(39, input);
                                throw eee;
                        }
                        cnt39++;
                    } while (true);

                    if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:285: ( '+' | '-' )?
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0=='+'||LA40_0=='-') ) {
                        alt40=1;
                    }
                    switch (alt40) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:296: ( '0' .. '9' )+
                    int cnt41=0;
                    loop41:
                    do {
                        int alt41=2;
                        int LA41_0 = input.LA(1);

                        if ( ((LA41_0>='0' && LA41_0<='9')) ) {
                            alt41=1;
                        }


                        switch (alt41) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7871:297: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt41 >= 1 ) break loop41;
                                EarlyExitException eee =
                                    new EarlyExitException(41, input);
                                throw eee;
                        }
                        cnt41++;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7873:14: ( ( '0' .. '9' )+ )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7873:16: ( '0' .. '9' )+
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7873:16: ( '0' .. '9' )+
            int cnt43=0;
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( ((LA43_0>='0' && LA43_0<='9')) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7873:17: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt43 >= 1 ) break loop43;
                        EarlyExitException eee =
                            new EarlyExitException(43, input);
                        throw eee;
                }
                cnt43++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_NATURAL"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7875:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7875:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7875:11: ( '^' )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0=='^') ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7875:11: '^'
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7875:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop45:
            do {
                int alt45=2;
                int LA45_0 = input.LA(1);

                if ( ((LA45_0>='0' && LA45_0<='9')||(LA45_0>='A' && LA45_0<='Z')||LA45_0=='_'||(LA45_0>='a' && LA45_0<='z')) ) {
                    alt45=1;
                }


                switch (alt45) {
            	case 1 :
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:
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
            	    break loop45;
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

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7877:13: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7877:15: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
            {
            match('\"'); 
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7877:19: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
            loop46:
            do {
                int alt46=3;
                int LA46_0 = input.LA(1);

                if ( (LA46_0=='\\') ) {
                    alt46=1;
                }
                else if ( ((LA46_0>='\u0000' && LA46_0<='!')||(LA46_0>='#' && LA46_0<='[')||(LA46_0>=']' && LA46_0<='\uFFFF')) ) {
                    alt46=2;
                }


                switch (alt46) {
            	case 1 :
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7877:20: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
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
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7877:65: ~ ( ( '\\\\' | '\"' ) )
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
            	    break loop46;
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

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7879:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7879:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7879:24: ( options {greedy=false; } : . )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0=='*') ) {
                    int LA47_1 = input.LA(2);

                    if ( (LA47_1=='/') ) {
                        alt47=2;
                    }
                    else if ( ((LA47_1>='\u0000' && LA47_1<='.')||(LA47_1>='0' && LA47_1<='\uFFFF')) ) {
                        alt47=1;
                    }


                }
                else if ( ((LA47_0>='\u0000' && LA47_0<=')')||(LA47_0>='+' && LA47_0<='\uFFFF')) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7879:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop47;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7881:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7881:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7881:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( ((LA48_0>='\u0000' && LA48_0<='\t')||(LA48_0>='\u000B' && LA48_0<='\f')||(LA48_0>='\u000E' && LA48_0<='\uFFFF')) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7881:24: ~ ( ( '\\n' | '\\r' ) )
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
            	    break loop48;
                }
            } while (true);

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7881:40: ( ( '\\r' )? '\\n' )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0=='\n'||LA50_0=='\r') ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7881:41: ( '\\r' )? '\\n'
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7881:41: ( '\\r' )?
                    int alt49=2;
                    int LA49_0 = input.LA(1);

                    if ( (LA49_0=='\r') ) {
                        alt49=1;
                    }
                    switch (alt49) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7881:41: '\\r'
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7883:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7883:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:7883:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt51=0;
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( ((LA51_0>='\t' && LA51_0<='\n')||LA51_0=='\r'||LA51_0==' ') ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:
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
            	    if ( cnt51 >= 1 ) break loop51;
                        EarlyExitException eee =
                            new EarlyExitException(51, input);
                        throw eee;
                }
                cnt51++;
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
        // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:8: ( T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | T__112 | T__113 | T__114 | T__115 | T__116 | T__117 | T__118 | T__119 | T__120 | T__121 | T__122 | T__123 | T__124 | T__125 | T__126 | T__127 | T__128 | T__129 | T__130 | T__131 | T__132 | T__133 | T__134 | T__135 | T__136 | T__137 | RULE_BOOLEAN | RULE_RED | RULE_GREEN | RULE_BLUE | RULE_ALPHA | RULE_FSIZE | RULE_DEGREES | RULE_PERCENT | RULE_TFLOAT | RULE_NATURAL | RULE_ID | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS )
        int alt52=134;
        alt52 = dfa52.predict(input);
        switch (alt52) {
            case 1 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:10: T__19
                {
                mT__19(); 

                }
                break;
            case 2 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:16: T__20
                {
                mT__20(); 

                }
                break;
            case 3 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:22: T__21
                {
                mT__21(); 

                }
                break;
            case 4 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:28: T__22
                {
                mT__22(); 

                }
                break;
            case 5 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:34: T__23
                {
                mT__23(); 

                }
                break;
            case 6 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:40: T__24
                {
                mT__24(); 

                }
                break;
            case 7 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:46: T__25
                {
                mT__25(); 

                }
                break;
            case 8 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:52: T__26
                {
                mT__26(); 

                }
                break;
            case 9 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:58: T__27
                {
                mT__27(); 

                }
                break;
            case 10 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:64: T__28
                {
                mT__28(); 

                }
                break;
            case 11 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:70: T__29
                {
                mT__29(); 

                }
                break;
            case 12 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:76: T__30
                {
                mT__30(); 

                }
                break;
            case 13 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:82: T__31
                {
                mT__31(); 

                }
                break;
            case 14 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:88: T__32
                {
                mT__32(); 

                }
                break;
            case 15 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:94: T__33
                {
                mT__33(); 

                }
                break;
            case 16 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:100: T__34
                {
                mT__34(); 

                }
                break;
            case 17 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:106: T__35
                {
                mT__35(); 

                }
                break;
            case 18 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:112: T__36
                {
                mT__36(); 

                }
                break;
            case 19 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:118: T__37
                {
                mT__37(); 

                }
                break;
            case 20 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:124: T__38
                {
                mT__38(); 

                }
                break;
            case 21 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:130: T__39
                {
                mT__39(); 

                }
                break;
            case 22 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:136: T__40
                {
                mT__40(); 

                }
                break;
            case 23 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:142: T__41
                {
                mT__41(); 

                }
                break;
            case 24 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:148: T__42
                {
                mT__42(); 

                }
                break;
            case 25 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:154: T__43
                {
                mT__43(); 

                }
                break;
            case 26 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:160: T__44
                {
                mT__44(); 

                }
                break;
            case 27 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:166: T__45
                {
                mT__45(); 

                }
                break;
            case 28 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:172: T__46
                {
                mT__46(); 

                }
                break;
            case 29 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:178: T__47
                {
                mT__47(); 

                }
                break;
            case 30 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:184: T__48
                {
                mT__48(); 

                }
                break;
            case 31 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:190: T__49
                {
                mT__49(); 

                }
                break;
            case 32 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:196: T__50
                {
                mT__50(); 

                }
                break;
            case 33 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:202: T__51
                {
                mT__51(); 

                }
                break;
            case 34 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:208: T__52
                {
                mT__52(); 

                }
                break;
            case 35 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:214: T__53
                {
                mT__53(); 

                }
                break;
            case 36 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:220: T__54
                {
                mT__54(); 

                }
                break;
            case 37 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:226: T__55
                {
                mT__55(); 

                }
                break;
            case 38 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:232: T__56
                {
                mT__56(); 

                }
                break;
            case 39 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:238: T__57
                {
                mT__57(); 

                }
                break;
            case 40 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:244: T__58
                {
                mT__58(); 

                }
                break;
            case 41 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:250: T__59
                {
                mT__59(); 

                }
                break;
            case 42 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:256: T__60
                {
                mT__60(); 

                }
                break;
            case 43 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:262: T__61
                {
                mT__61(); 

                }
                break;
            case 44 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:268: T__62
                {
                mT__62(); 

                }
                break;
            case 45 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:274: T__63
                {
                mT__63(); 

                }
                break;
            case 46 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:280: T__64
                {
                mT__64(); 

                }
                break;
            case 47 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:286: T__65
                {
                mT__65(); 

                }
                break;
            case 48 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:292: T__66
                {
                mT__66(); 

                }
                break;
            case 49 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:298: T__67
                {
                mT__67(); 

                }
                break;
            case 50 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:304: T__68
                {
                mT__68(); 

                }
                break;
            case 51 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:310: T__69
                {
                mT__69(); 

                }
                break;
            case 52 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:316: T__70
                {
                mT__70(); 

                }
                break;
            case 53 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:322: T__71
                {
                mT__71(); 

                }
                break;
            case 54 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:328: T__72
                {
                mT__72(); 

                }
                break;
            case 55 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:334: T__73
                {
                mT__73(); 

                }
                break;
            case 56 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:340: T__74
                {
                mT__74(); 

                }
                break;
            case 57 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:346: T__75
                {
                mT__75(); 

                }
                break;
            case 58 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:352: T__76
                {
                mT__76(); 

                }
                break;
            case 59 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:358: T__77
                {
                mT__77(); 

                }
                break;
            case 60 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:364: T__78
                {
                mT__78(); 

                }
                break;
            case 61 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:370: T__79
                {
                mT__79(); 

                }
                break;
            case 62 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:376: T__80
                {
                mT__80(); 

                }
                break;
            case 63 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:382: T__81
                {
                mT__81(); 

                }
                break;
            case 64 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:388: T__82
                {
                mT__82(); 

                }
                break;
            case 65 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:394: T__83
                {
                mT__83(); 

                }
                break;
            case 66 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:400: T__84
                {
                mT__84(); 

                }
                break;
            case 67 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:406: T__85
                {
                mT__85(); 

                }
                break;
            case 68 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:412: T__86
                {
                mT__86(); 

                }
                break;
            case 69 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:418: T__87
                {
                mT__87(); 

                }
                break;
            case 70 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:424: T__88
                {
                mT__88(); 

                }
                break;
            case 71 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:430: T__89
                {
                mT__89(); 

                }
                break;
            case 72 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:436: T__90
                {
                mT__90(); 

                }
                break;
            case 73 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:442: T__91
                {
                mT__91(); 

                }
                break;
            case 74 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:448: T__92
                {
                mT__92(); 

                }
                break;
            case 75 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:454: T__93
                {
                mT__93(); 

                }
                break;
            case 76 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:460: T__94
                {
                mT__94(); 

                }
                break;
            case 77 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:466: T__95
                {
                mT__95(); 

                }
                break;
            case 78 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:472: T__96
                {
                mT__96(); 

                }
                break;
            case 79 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:478: T__97
                {
                mT__97(); 

                }
                break;
            case 80 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:484: T__98
                {
                mT__98(); 

                }
                break;
            case 81 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:490: T__99
                {
                mT__99(); 

                }
                break;
            case 82 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:496: T__100
                {
                mT__100(); 

                }
                break;
            case 83 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:503: T__101
                {
                mT__101(); 

                }
                break;
            case 84 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:510: T__102
                {
                mT__102(); 

                }
                break;
            case 85 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:517: T__103
                {
                mT__103(); 

                }
                break;
            case 86 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:524: T__104
                {
                mT__104(); 

                }
                break;
            case 87 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:531: T__105
                {
                mT__105(); 

                }
                break;
            case 88 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:538: T__106
                {
                mT__106(); 

                }
                break;
            case 89 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:545: T__107
                {
                mT__107(); 

                }
                break;
            case 90 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:552: T__108
                {
                mT__108(); 

                }
                break;
            case 91 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:559: T__109
                {
                mT__109(); 

                }
                break;
            case 92 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:566: T__110
                {
                mT__110(); 

                }
                break;
            case 93 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:573: T__111
                {
                mT__111(); 

                }
                break;
            case 94 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:580: T__112
                {
                mT__112(); 

                }
                break;
            case 95 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:587: T__113
                {
                mT__113(); 

                }
                break;
            case 96 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:594: T__114
                {
                mT__114(); 

                }
                break;
            case 97 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:601: T__115
                {
                mT__115(); 

                }
                break;
            case 98 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:608: T__116
                {
                mT__116(); 

                }
                break;
            case 99 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:615: T__117
                {
                mT__117(); 

                }
                break;
            case 100 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:622: T__118
                {
                mT__118(); 

                }
                break;
            case 101 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:629: T__119
                {
                mT__119(); 

                }
                break;
            case 102 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:636: T__120
                {
                mT__120(); 

                }
                break;
            case 103 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:643: T__121
                {
                mT__121(); 

                }
                break;
            case 104 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:650: T__122
                {
                mT__122(); 

                }
                break;
            case 105 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:657: T__123
                {
                mT__123(); 

                }
                break;
            case 106 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:664: T__124
                {
                mT__124(); 

                }
                break;
            case 107 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:671: T__125
                {
                mT__125(); 

                }
                break;
            case 108 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:678: T__126
                {
                mT__126(); 

                }
                break;
            case 109 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:685: T__127
                {
                mT__127(); 

                }
                break;
            case 110 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:692: T__128
                {
                mT__128(); 

                }
                break;
            case 111 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:699: T__129
                {
                mT__129(); 

                }
                break;
            case 112 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:706: T__130
                {
                mT__130(); 

                }
                break;
            case 113 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:713: T__131
                {
                mT__131(); 

                }
                break;
            case 114 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:720: T__132
                {
                mT__132(); 

                }
                break;
            case 115 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:727: T__133
                {
                mT__133(); 

                }
                break;
            case 116 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:734: T__134
                {
                mT__134(); 

                }
                break;
            case 117 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:741: T__135
                {
                mT__135(); 

                }
                break;
            case 118 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:748: T__136
                {
                mT__136(); 

                }
                break;
            case 119 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:755: T__137
                {
                mT__137(); 

                }
                break;
            case 120 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:762: RULE_BOOLEAN
                {
                mRULE_BOOLEAN(); 

                }
                break;
            case 121 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:775: RULE_RED
                {
                mRULE_RED(); 

                }
                break;
            case 122 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:784: RULE_GREEN
                {
                mRULE_GREEN(); 

                }
                break;
            case 123 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:795: RULE_BLUE
                {
                mRULE_BLUE(); 

                }
                break;
            case 124 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:805: RULE_ALPHA
                {
                mRULE_ALPHA(); 

                }
                break;
            case 125 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:816: RULE_FSIZE
                {
                mRULE_FSIZE(); 

                }
                break;
            case 126 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:827: RULE_DEGREES
                {
                mRULE_DEGREES(); 

                }
                break;
            case 127 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:840: RULE_PERCENT
                {
                mRULE_PERCENT(); 

                }
                break;
            case 128 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:853: RULE_TFLOAT
                {
                mRULE_TFLOAT(); 

                }
                break;
            case 129 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:865: RULE_NATURAL
                {
                mRULE_NATURAL(); 

                }
                break;
            case 130 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:878: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 131 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:886: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 132 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:898: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 133 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:914: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 134 :
                // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1:930: RULE_WS
                {
                mRULE_WS(); 

                }
                break;

        }

    }


    protected DFA42 dfa42 = new DFA42(this);
    protected DFA52 dfa52 = new DFA52(this);
    static final String DFA42_eotS =
        "\6\uffff";
    static final String DFA42_eofS =
        "\6\uffff";
    static final String DFA42_minS =
        "\1\53\1\uffff\1\56\3\uffff";
    static final String DFA42_maxS =
        "\1\71\1\uffff\1\145\3\uffff";
    static final String DFA42_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\1\4\1\2";
    static final String DFA42_specialS =
        "\6\uffff}>";
    static final String[] DFA42_transitionS = {
            "\1\1\1\uffff\1\1\1\3\1\uffff\12\2",
            "",
            "\1\5\1\uffff\12\2\13\uffff\1\4\37\uffff\1\4",
            "",
            "",
            ""
    };

    static final short[] DFA42_eot = DFA.unpackEncodedString(DFA42_eotS);
    static final short[] DFA42_eof = DFA.unpackEncodedString(DFA42_eofS);
    static final char[] DFA42_min = DFA.unpackEncodedStringToUnsignedChars(DFA42_minS);
    static final char[] DFA42_max = DFA.unpackEncodedStringToUnsignedChars(DFA42_maxS);
    static final short[] DFA42_accept = DFA.unpackEncodedString(DFA42_acceptS);
    static final short[] DFA42_special = DFA.unpackEncodedString(DFA42_specialS);
    static final short[][] DFA42_transition;

    static {
        int numStates = DFA42_transitionS.length;
        DFA42_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA42_transition[i] = DFA.unpackEncodedString(DFA42_transitionS[i]);
        }
    }

    class DFA42 extends DFA {

        public DFA42(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 42;
            this.eot = DFA42_eot;
            this.eof = DFA42_eof;
            this.min = DFA42_min;
            this.max = DFA42_max;
            this.accept = DFA42_accept;
            this.special = DFA42_special;
            this.transition = DFA42_transition;
        }
        public String getDescription() {
            return "7871:15: ( ( '+' | '-' ) ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? ) | ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )";
        }
    }
    static final String DFA52_eotS =
        "\1\uffff\1\50\6\uffff\1\73\2\uffff\1\50\1\100\1\102\10\50\2\uffff"+
        "\1\50\1\uffff\11\50\1\160\1\162\2\50\1\176\4\uffff\13\50\1\uffff"+
        "\1\173\3\uffff\4\50\1\uffff\1\50\1\uffff\55\50\1\uffff\1\173\1\uffff"+
        "\2\50\1\uffff\1\173\12\uffff\17\50\2\173\1\u00e2\2\50\1\u00e5\20"+
        "\50\1\u00f8\44\50\1\u011f\3\50\1\173\15\50\1\u0130\3\50\1\173\1"+
        "\uffff\2\50\1\uffff\2\50\1\u0139\17\50\1\uffff\1\u0149\1\50\1\u014b"+
        "\2\50\1\u014e\1\50\1\u0153\17\50\1\u0163\1\u0164\2\50\1\u0168\4"+
        "\50\1\u016e\5\50\1\uffff\1\u0175\1\u0176\2\50\1\u0179\1\u017a\2"+
        "\50\1\u017d\6\50\1\u0185\1\uffff\10\50\1\uffff\2\50\1\u0191\3\50"+
        "\1\u0195\1\u0196\7\50\1\uffff\1\50\1\uffff\1\50\1\u01a0\1\uffff"+
        "\4\50\1\uffff\1\u01a5\1\50\1\u01a8\12\50\1\u01b3\1\50\2\uffff\3"+
        "\50\1\uffff\1\u0149\4\50\1\uffff\1\50\1\u01bd\3\50\3\uffff\1\u01c2"+
        "\1\u01c3\2\uffff\1\50\1\u01c5\1\uffff\7\50\1\uffff\1\u01cd\2\50"+
        "\1\u01d0\5\50\1\u01d7\1\u01d8\1\uffff\1\u01d9\1\50\1\u01db\2\uffff"+
        "\1\u01dc\1\u01dd\1\50\1\u01df\1\50\1\u01e1\1\50\1\u01e4\1\50\1\uffff"+
        "\4\50\1\uffff\2\50\1\uffff\3\50\1\u01ef\6\50\1\uffff\5\50\1\u01fb"+
        "\3\50\1\uffff\1\u01ff\1\50\1\u0202\3\uffff\1\50\1\uffff\7\50\1\uffff"+
        "\1\u020c\1\50\1\uffff\3\50\1\u0211\1\u0212\1\50\3\uffff\1\50\3\uffff"+
        "\1\50\1\uffff\1\50\1\uffff\2\50\1\uffff\1\50\1\u021a\7\50\1\u0222"+
        "\1\uffff\13\50\1\uffff\2\50\1\u0233\1\uffff\2\50\2\uffff\1\u0237"+
        "\1\50\1\u0239\5\50\1\uffff\4\50\2\uffff\1\50\1\u0245\5\50\1\uffff"+
        "\1\u024b\2\50\1\u024e\3\50\1\uffff\1\u0252\1\50\1\u0254\1\u0255"+
        "\7\50\1\u025d\1\u025e\2\50\1\u0262\1\uffff\2\50\1\u0266\1\uffff"+
        "\1\u0267\1\uffff\7\50\1\u026f\1\50\1\u0271\1\50\1\uffff\1\50\1\u0274"+
        "\3\50\1\uffff\1\u0278\1\u0279\1\uffff\1\50\1\u027c\1\50\1\uffff"+
        "\1\50\2\uffff\7\50\2\uffff\1\u0286\2\50\1\uffff\2\50\3\uffff\1\u028c"+
        "\1\u028d\2\50\1\u0290\2\50\1\uffff\1\u0293\1\uffff\2\50\1\uffff"+
        "\2\50\1\u0299\2\uffff\2\50\1\uffff\10\50\1\u02a4\1\uffff\5\50\2"+
        "\uffff\2\50\1\uffff\2\50\1\uffff\1\u02ae\4\50\1\uffff\2\50\1\u02b5"+
        "\1\u02b6\6\50\1\uffff\3\50\1\u02c0\5\50\1\uffff\6\50\2\uffff\1\u02cc"+
        "\1\50\1\u02ce\1\50\1\u02d0\4\50\1\uffff\7\50\1\u02dc\3\50\1\uffff"+
        "\1\u02e0\1\uffff\1\u02e1\1\uffff\1\u02e2\2\50\1\u02e5\7\50\1\uffff"+
        "\1\50\1\u02ee\1\u02ef\3\uffff\1\50\1\u02f1\1\uffff\4\50\1\u02f6"+
        "\3\50\2\uffff\1\50\1\uffff\2\50\1\u02fd\1\u02fe\1\uffff\1\50\1\u0300"+
        "\2\50\1\u0303\1\u0304\2\uffff\1\50\1\uffff\1\u0306\1\u0307\2\uffff"+
        "\1\50\2\uffff\1\u0309\1\uffff";
    static final String DFA52_eofS =
        "\u030a\uffff";
    static final String DFA52_minS =
        "\1\11\1\141\4\uffff\1\56\1\uffff\1\76\2\uffff\1\151\2\60\1\143\1"+
        "\151\1\101\1\156\1\157\1\141\2\145\2\uffff\1\142\1\uffff\1\165\1"+
        "\141\1\157\1\141\1\156\1\101\1\162\1\145\1\141\1\56\1\60\1\160\1"+
        "\162\1\45\2\uffff\1\52\1\uffff\1\162\1\157\1\144\1\141\1\157\1\145"+
        "\1\150\1\145\1\162\1\155\1\160\1\uffff\1\45\1\60\2\uffff\1\151\1"+
        "\157\1\145\1\146\1\uffff\1\146\1\uffff\1\156\1\171\2\141\1\165\1"+
        "\154\1\144\1\151\1\154\1\162\1\163\1\141\1\160\1\165\1\154\1\143"+
        "\1\166\1\146\1\156\1\147\1\164\1\146\1\164\1\143\1\145\1\163\1\156"+
        "\1\144\1\156\1\170\1\154\2\156\1\141\1\154\1\144\1\154\1\162\1\151"+
        "\1\154\1\157\1\156\1\143\1\164\1\163\1\uffff\1\45\1\uffff\1\145"+
        "\1\162\1\uffff\1\45\12\uffff\1\141\1\144\1\147\1\154\1\142\1\154"+
        "\1\143\1\165\1\151\1\163\1\170\1\143\1\141\1\154\1\171\2\45\1\60"+
        "\1\156\1\160\1\60\2\146\1\145\1\147\2\154\1\144\1\141\1\151\1\164"+
        "\1\147\2\151\1\145\1\151\1\154\1\60\1\145\1\164\1\144\1\153\1\145"+
        "\1\164\1\145\1\150\1\141\1\156\1\145\1\141\1\151\1\150\1\141\1\157"+
        "\1\143\1\151\1\103\1\145\1\103\1\154\2\145\2\164\1\163\1\145\1\151"+
        "\1\164\1\144\1\165\1\162\1\164\1\157\1\142\1\60\1\150\1\156\1\157"+
        "\1\45\1\160\2\145\1\151\1\145\1\164\1\171\1\144\1\164\1\156\1\154"+
        "\2\164\1\60\1\147\1\151\1\154\1\45\1\uffff\1\164\1\141\1\uffff\2"+
        "\163\1\60\1\154\2\145\1\157\1\147\1\162\1\144\2\150\1\147\1\172"+
        "\1\164\1\163\1\151\1\145\1\uffff\1\60\1\157\1\60\1\147\1\154\1\60"+
        "\1\103\1\60\2\164\1\144\1\162\1\164\2\157\1\104\1\154\1\164\1\146"+
        "\1\155\1\145\1\162\1\145\2\60\1\147\1\116\1\60\1\145\1\162\1\147"+
        "\1\151\1\60\1\155\1\144\1\145\1\162\1\154\1\uffff\1\55\1\60\1\162"+
        "\1\150\2\60\1\160\1\154\1\60\1\147\1\145\1\141\2\144\1\157\1\60"+
        "\1\uffff\1\145\1\156\1\145\1\104\1\162\1\147\2\145\1\uffff\1\145"+
        "\1\163\1\60\1\167\1\147\1\145\2\60\1\164\1\156\1\157\1\163\1\151"+
        "\1\143\1\146\1\uffff\1\155\1\uffff\1\162\1\60\1\uffff\1\141\1\157"+
        "\1\164\1\151\1\uffff\1\60\1\145\1\60\1\145\1\151\1\156\1\162\1\141"+
        "\1\165\2\151\1\141\1\154\1\60\1\154\2\uffff\1\162\1\141\1\151\1"+
        "\uffff\1\60\1\154\1\156\1\143\1\141\1\uffff\1\156\1\60\1\162\1\141"+
        "\1\145\1\144\2\uffff\2\60\2\uffff\1\163\1\60\1\uffff\1\151\1\157"+
        "\1\162\1\156\1\145\1\101\1\155\1\uffff\1\60\1\145\1\163\1\60\1\141"+
        "\1\164\1\141\2\164\2\60\1\uffff\1\60\1\154\1\60\2\uffff\2\60\1\156"+
        "\1\60\1\142\1\60\1\164\1\60\1\157\1\uffff\1\160\1\151\1\171\1\144"+
        "\1\uffff\1\157\1\127\1\uffff\1\156\1\166\1\163\1\60\2\164\1\157"+
        "\1\145\2\154\1\uffff\1\154\1\157\1\155\1\172\1\151\1\60\1\141\1"+
        "\164\1\163\1\uffff\1\60\1\164\1\60\1\157\2\uffff\1\145\1\uffff\2"+
        "\156\1\151\1\147\1\144\1\162\1\122\1\uffff\1\60\1\124\1\uffff\1"+
        "\164\1\151\1\164\2\60\1\154\3\uffff\1\145\3\uffff\1\164\1\uffff"+
        "\1\154\1\uffff\1\101\1\151\1\uffff\1\165\1\60\1\156\1\154\1\164"+
        "\1\156\1\151\1\143\1\145\1\60\1\uffff\1\141\1\145\1\156\1\162\3"+
        "\110\1\165\2\145\1\156\1\uffff\1\154\1\141\1\60\1\uffff\1\157\1"+
        "\154\1\uffff\1\164\1\60\1\145\1\60\1\156\1\154\1\120\2\145\1\uffff"+
        "\1\145\1\141\2\145\2\uffff\1\151\1\60\1\141\1\145\1\156\1\147\1"+
        "\156\1\uffff\1\60\1\145\1\150\1\60\1\164\1\145\1\120\1\uffff\1\60"+
        "\1\120\2\60\1\151\1\145\1\151\1\145\1\151\1\145\1\156\2\60\1\145"+
        "\1\101\1\60\1\uffff\1\162\1\151\1\55\1\uffff\1\60\1\uffff\1\147"+
        "\2\145\1\157\1\141\1\156\1\155\1\60\1\163\1\60\1\143\1\uffff\1\154"+
        "\1\60\1\143\1\150\1\144\1\uffff\2\60\1\uffff\1\150\1\60\1\157\1"+
        "\uffff\1\157\2\uffff\1\144\1\151\1\144\1\151\1\144\1\151\1\144\2"+
        "\uffff\1\60\1\154\1\141\1\uffff\1\104\1\143\3\uffff\2\60\1\143\1"+
        "\154\1\60\1\144\1\160\1\uffff\1\60\1\uffff\1\153\1\101\1\uffff\1"+
        "\150\1\164\1\60\2\uffff\1\114\1\157\1\uffff\2\163\1\164\1\147\1"+
        "\164\1\147\1\164\1\147\1\60\1\uffff\1\151\1\162\1\141\1\153\1\151"+
        "\2\uffff\1\164\1\171\1\uffff\1\145\1\154\1\uffff\1\60\1\154\1\141"+
        "\1\157\1\101\1\uffff\2\151\2\60\6\150\1\uffff\2\147\1\164\1\60\1"+
        "\142\1\141\1\154\1\162\1\141\1\uffff\1\151\2\162\3\156\2\uffff\1"+
        "\60\1\164\1\60\1\164\1\60\1\164\1\156\1\151\1\141\1\uffff\1\162"+
        "\1\156\2\151\1\164\2\147\1\60\1\143\1\145\1\164\1\uffff\1\60\1\uffff"+
        "\1\60\1\uffff\1\60\1\155\1\156\1\60\1\141\1\147\2\156\1\145\1\156"+
        "\1\151\1\uffff\1\150\2\60\3\uffff\1\145\1\60\1\uffff\1\162\1\154"+
        "\1\145\1\147\1\60\1\155\1\156\1\157\2\uffff\1\156\1\uffff\1\171"+
        "\1\145\2\60\1\uffff\1\145\1\60\1\162\1\164\2\60\2\uffff\1\156\1"+
        "\uffff\2\60\2\uffff\1\164\2\uffff\1\60\1\uffff";
    static final String DFA52_maxS =
        "\1\175\1\164\4\uffff\1\76\1\uffff\1\76\2\uffff\1\162\2\172\1\164"+
        "\1\151\1\157\1\164\1\162\1\157\1\151\1\157\2\uffff\1\162\1\uffff"+
        "\1\165\1\157\1\165\1\157\1\156\1\145\1\162\2\157\2\71\1\160\2\162"+
        "\2\uffff\1\57\1\uffff\1\162\1\157\1\154\1\141\2\157\1\165\1\145"+
        "\1\162\1\155\1\164\1\uffff\1\144\1\71\2\uffff\1\163\1\157\1\145"+
        "\1\146\1\uffff\1\146\1\uffff\1\172\1\171\2\141\1\165\1\154\1\144"+
        "\1\151\1\154\1\162\1\166\1\141\1\160\1\165\1\164\1\143\1\166\1\146"+
        "\1\156\1\147\1\165\1\154\1\164\1\143\1\145\1\163\1\156\1\144\1\164"+
        "\1\170\1\154\1\156\1\162\1\141\1\154\1\144\1\154\1\162\1\151\1\154"+
        "\1\157\1\156\1\143\1\165\1\163\1\uffff\1\144\1\uffff\1\145\1\162"+
        "\1\uffff\1\144\12\uffff\1\141\1\144\1\147\1\154\1\142\1\162\1\156"+
        "\1\165\1\151\1\163\1\170\1\143\1\141\1\154\1\171\2\144\1\172\1\156"+
        "\1\160\1\172\2\146\1\145\1\147\2\154\1\144\2\151\1\164\1\147\2\151"+
        "\1\145\1\151\1\154\1\172\1\145\1\164\1\144\1\153\1\145\1\164\1\153"+
        "\1\150\1\141\1\156\1\145\1\141\1\151\1\150\1\141\1\157\1\143\2\151"+
        "\1\145\1\103\1\154\2\145\2\164\1\163\1\145\1\151\1\164\1\144\1\165"+
        "\1\162\1\164\1\157\1\142\1\172\1\150\1\156\1\157\1\144\1\160\2\145"+
        "\1\151\1\145\1\164\1\171\1\144\1\164\1\156\1\154\2\164\1\172\1\147"+
        "\1\151\1\154\1\144\1\uffff\1\164\1\145\1\uffff\2\163\1\172\1\154"+
        "\2\145\1\157\1\147\1\162\1\144\2\150\1\147\1\172\1\164\1\163\1\151"+
        "\1\145\1\uffff\1\172\1\157\1\172\1\147\1\154\1\172\1\127\1\172\2"+
        "\164\1\144\1\162\1\164\2\157\1\104\1\154\1\164\1\146\1\155\1\145"+
        "\1\162\1\145\2\172\1\147\1\123\1\172\1\145\1\162\1\147\1\151\1\172"+
        "\1\155\1\144\1\145\1\162\1\154\1\uffff\2\172\1\162\1\150\2\172\1"+
        "\160\1\154\1\172\1\154\1\145\1\141\2\144\1\157\1\172\1\uffff\1\145"+
        "\1\156\1\145\1\163\1\162\1\147\2\145\1\uffff\1\145\1\163\1\172\1"+
        "\167\1\147\1\145\2\172\1\164\1\156\1\157\1\163\1\151\1\143\1\146"+
        "\1\uffff\1\155\1\uffff\1\162\1\172\1\uffff\1\141\1\157\1\164\1\151"+
        "\1\uffff\1\172\1\151\1\172\1\145\1\151\1\156\1\162\1\141\1\165\2"+
        "\151\1\141\1\154\1\172\1\154\2\uffff\1\162\1\141\1\151\1\uffff\1"+
        "\172\1\154\1\156\1\143\1\141\1\uffff\1\156\1\172\1\162\1\141\1\145"+
        "\1\144\2\uffff\2\172\2\uffff\1\163\1\172\1\uffff\1\151\1\157\1\162"+
        "\1\156\1\145\1\101\1\155\1\uffff\1\172\1\145\1\163\1\172\1\141\1"+
        "\164\1\141\2\164\2\172\1\uffff\1\172\1\154\1\172\2\uffff\2\172\1"+
        "\156\1\172\1\142\1\172\1\164\1\172\1\157\1\uffff\1\160\1\151\1\171"+
        "\1\144\1\uffff\1\157\1\127\1\uffff\1\156\1\166\1\163\1\172\2\164"+
        "\1\157\1\145\2\154\1\uffff\1\154\1\157\1\155\1\172\1\151\1\172\1"+
        "\141\1\164\1\163\1\uffff\1\172\1\164\1\172\1\157\2\uffff\1\145\1"+
        "\uffff\2\156\1\151\1\147\1\144\1\162\1\122\1\uffff\1\172\1\124\1"+
        "\uffff\1\164\1\151\1\164\2\172\1\154\3\uffff\1\145\3\uffff\1\164"+
        "\1\uffff\1\154\1\uffff\1\101\1\151\1\uffff\1\165\1\172\1\156\1\154"+
        "\1\164\1\156\1\151\1\143\1\145\1\172\1\uffff\1\141\1\145\1\156\1"+
        "\162\3\127\1\165\2\145\1\156\1\uffff\1\154\1\141\1\172\1\uffff\1"+
        "\157\1\154\1\uffff\1\164\1\172\1\145\1\172\1\156\1\154\1\122\2\145"+
        "\1\uffff\1\145\1\141\2\145\2\uffff\1\151\1\172\1\141\1\145\1\156"+
        "\1\147\1\156\1\uffff\1\172\1\145\1\150\1\172\1\164\1\145\1\120\1"+
        "\uffff\1\172\1\120\2\172\1\151\1\145\1\151\1\145\1\151\1\145\1\156"+
        "\2\172\1\145\1\115\1\172\1\uffff\1\162\1\151\1\55\1\uffff\1\172"+
        "\1\uffff\1\147\2\145\1\157\1\141\1\156\1\155\1\172\1\163\1\172\1"+
        "\143\1\uffff\1\154\1\172\1\143\1\150\1\144\1\uffff\2\172\1\uffff"+
        "\1\150\1\172\1\157\1\uffff\1\157\2\uffff\1\144\1\151\1\144\1\151"+
        "\1\144\1\151\1\144\2\uffff\1\172\1\154\1\141\1\uffff\1\104\1\143"+
        "\3\uffff\2\172\1\143\1\154\1\172\1\144\1\160\1\uffff\1\172\1\uffff"+
        "\1\153\1\115\1\uffff\1\150\1\164\1\172\2\uffff\1\114\1\157\1\uffff"+
        "\2\163\1\164\1\147\1\164\1\147\1\164\1\147\1\172\1\uffff\1\151\1"+
        "\162\1\141\1\153\1\151\2\uffff\1\164\1\171\1\uffff\1\145\1\154\1"+
        "\uffff\1\172\1\154\1\141\1\157\1\101\1\uffff\2\151\2\172\6\150\1"+
        "\uffff\2\147\1\164\1\172\1\142\1\141\1\154\1\162\1\141\1\uffff\1"+
        "\151\2\162\3\156\2\uffff\1\172\1\164\1\172\1\164\1\172\1\164\1\156"+
        "\1\151\1\141\1\uffff\1\162\1\156\2\151\1\164\2\147\1\172\1\143\1"+
        "\145\1\164\1\uffff\1\172\1\uffff\1\172\1\uffff\1\172\1\155\1\156"+
        "\1\172\1\141\1\147\2\156\1\145\1\156\1\151\1\uffff\1\150\2\172\3"+
        "\uffff\1\145\1\172\1\uffff\1\162\1\154\1\145\1\147\1\172\1\155\1"+
        "\156\1\157\2\uffff\1\156\1\uffff\1\171\1\145\2\172\1\uffff\1\145"+
        "\1\172\1\162\1\164\2\172\2\uffff\1\156\1\uffff\2\172\2\uffff\1\164"+
        "\2\uffff\1\172\1\uffff";
    static final String DFA52_acceptS =
        "\2\uffff\1\3\1\4\1\6\1\7\1\uffff\1\11\1\uffff\1\15\1\16\13\uffff"+
        "\1\34\1\35\1\uffff\1\41\16\uffff\1\u0082\1\u0083\1\uffff\1\u0086"+
        "\13\uffff\1\10\2\uffff\1\137\1\14\4\uffff\1\20\1\uffff\1\21\55\uffff"+
        "\1\140\1\uffff\1\141\2\uffff\1\174\1\uffff\1\177\1\171\1\173\1\172"+
        "\1\u0080\1\176\1\175\1\u0081\1\u0084\1\u0085\141\uffff\1\17\2\uffff"+
        "\1\144\22\uffff\1\27\46\uffff\1\156\20\uffff\1\47\10\uffff\1\22"+
        "\17\uffff\1\170\1\uffff\1\64\2\uffff\1\31\4\uffff\1\153\17\uffff"+
        "\1\60\1\146\3\uffff\1\161\5\uffff\1\107\6\uffff\1\155\1\142\2\uffff"+
        "\1\2\1\5\2\uffff\1\13\7\uffff\1\43\13\uffff\1\67\3\uffff\1\154\1"+
        "\23\11\uffff\1\165\4\uffff\1\32\2\uffff\1\162\12\uffff\1\164\11"+
        "\uffff\1\143\4\uffff\1\151\1\1\1\uffff\1\12\7\uffff\1\51\2\uffff"+
        "\1\33\6\uffff\1\147\1\36\1\103\1\uffff\1\163\1\24\1\72\1\uffff\1"+
        "\26\1\uffff\1\65\2\uffff\1\30\12\uffff\1\102\13\uffff\1\73\3\uffff"+
        "\1\145\2\uffff\1\150\11\uffff\1\56\4\uffff\1\132\1\133\7\uffff\1"+
        "\75\7\uffff\1\37\20\uffff\1\112\3\uffff\1\46\1\uffff\1\54\13\uffff"+
        "\1\152\5\uffff\1\76\2\uffff\1\101\3\uffff\1\113\1\uffff\1\52\1\57"+
        "\7\uffff\1\66\1\70\3\uffff\1\124\2\uffff\1\160\1\157\1\53\7\uffff"+
        "\1\114\1\uffff\1\61\2\uffff\1\74\3\uffff\1\77\1\100\2\uffff\1\104"+
        "\11\uffff\1\71\5\uffff\1\40\1\44\2\uffff\1\42\2\uffff\1\25\5\uffff"+
        "\1\63\12\uffff\1\62\11\uffff\1\166\6\uffff\1\134\1\135\11\uffff"+
        "\1\167\13\uffff\1\116\1\uffff\1\125\1\uffff\1\127\13\uffff\1\110"+
        "\3\uffff\1\117\1\126\1\130\2\uffff\1\131\10\uffff\1\136\1\115\1"+
        "\uffff\1\123\4\uffff\1\106\6\uffff\1\55\1\50\1\uffff\1\122\2\uffff"+
        "\1\105\1\45\1\uffff\1\111\1\121\1\uffff\1\120";
    static final String DFA52_specialS =
        "\u030a\uffff}>";
    static final String[] DFA52_transitionS = {
            "\2\53\2\uffff\1\53\22\uffff\1\53\1\uffff\1\51\5\uffff\1\4\1"+
            "\7\1\31\1\43\1\27\1\6\1\44\1\52\12\47\1\5\1\26\1\uffff\1\10"+
            "\3\uffff\32\50\1\11\1\uffff\1\12\2\50\1\uffff\1\30\1\23\1\41"+
            "\1\42\1\46\1\35\1\40\1\20\1\21\1\32\1\1\1\24\1\33\1\34\1\45"+
            "\1\13\1\50\1\25\1\16\1\22\1\36\1\37\1\17\1\14\1\15\1\50\1\2"+
            "\1\uffff\1\3",
            "\1\64\1\uffff\1\62\1\uffff\1\56\1\uffff\1\54\1\uffff\1\65\2"+
            "\uffff\1\57\1\uffff\1\55\1\uffff\1\60\1\uffff\1\61\1\66\1\63",
            "",
            "",
            "",
            "",
            "\1\71\1\uffff\12\70\4\uffff\1\67",
            "",
            "\1\72",
            "",
            "",
            "\1\76\5\uffff\1\74\2\uffff\1\75",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\16\50\1\77\13\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\16\50\1\101\13\50",
            "\1\105\4\uffff\1\106\1\103\5\uffff\1\110\1\uffff\1\107\2\uffff"+
            "\1\104",
            "\1\111",
            "\1\113\43\uffff\1\112\11\uffff\1\114",
            "\1\115\5\uffff\1\116",
            "\1\117\2\uffff\1\120",
            "\1\122\3\uffff\1\123\11\uffff\1\121",
            "\1\124\3\uffff\1\125",
            "\1\130\3\uffff\1\126\5\uffff\1\127",
            "",
            "",
            "\1\134\1\131\12\uffff\1\132\3\uffff\1\133",
            "",
            "\1\135",
            "\1\140\7\uffff\1\137\5\uffff\1\136",
            "\1\142\5\uffff\1\141",
            "\1\145\12\uffff\1\144\2\uffff\1\143",
            "\1\146",
            "\1\147\43\uffff\1\150",
            "\1\151",
            "\1\154\2\uffff\1\153\6\uffff\1\152",
            "\1\157\3\uffff\1\155\11\uffff\1\156",
            "\1\71\1\uffff\12\70",
            "\12\161",
            "\1\163",
            "\1\164",
            "\1\167\10\uffff\1\166\1\uffff\12\47\13\uffff\1\173\33\uffff"+
            "\1\165\1\171\1\uffff\1\174\1\173\1\uffff\1\172\10\uffff\1\175"+
            "\1\uffff\1\170",
            "",
            "",
            "\1\177\4\uffff\1\u0080",
            "",
            "\1\u0081",
            "\1\u0082",
            "\1\u0083\7\uffff\1\u0084",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087\11\uffff\1\u0088",
            "\1\u0089\14\uffff\1\u008a",
            "\1\u008b",
            "\1\u008c",
            "\1\u008d",
            "\1\u008e\3\uffff\1\u008f",
            "",
            "\1\167\10\uffff\1\u0090\1\uffff\12\70\52\uffff\1\174",
            "\12\u0091",
            "",
            "",
            "\1\u0093\11\uffff\1\u0092",
            "\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "",
            "\1\u0097",
            "",
            "\1\u0099\13\uffff\1\u0098",
            "\1\u009a",
            "\1\u009b",
            "\1\u009c",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "\1\u00a0",
            "\1\u00a1",
            "\1\u00a2",
            "\1\u00a3\2\uffff\1\u00a4",
            "\1\u00a5",
            "\1\u00a6",
            "\1\u00a7",
            "\1\u00a9\7\uffff\1\u00a8",
            "\1\u00aa",
            "\1\u00ab",
            "\1\u00ac",
            "\1\u00ad",
            "\1\u00ae",
            "\1\u00af\1\u00b0",
            "\1\u00b1\5\uffff\1\u00b2",
            "\1\u00b3",
            "\1\u00b4",
            "\1\u00b5",
            "\1\u00b6",
            "\1\u00b7",
            "\1\u00b8",
            "\1\u00b9\5\uffff\1\u00ba",
            "\1\u00bb",
            "\1\u00bc",
            "\1\u00bd",
            "\1\u00bf\3\uffff\1\u00be",
            "\1\u00c0",
            "\1\u00c1",
            "\1\u00c2",
            "\1\u00c3",
            "\1\u00c4",
            "\1\u00c5",
            "\1\u00c6",
            "\1\u00c7",
            "\1\u00c8",
            "\1\u00c9",
            "\1\u00cb\1\u00ca",
            "\1\u00cc",
            "",
            "\1\167\12\uffff\12\161\52\uffff\1\174",
            "",
            "\1\u00cd",
            "\1\u00ce",
            "",
            "\1\167\12\uffff\12\u00cf\52\uffff\1\174",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u00d0",
            "\1\u00d1",
            "\1\u00d2",
            "\1\u00d3",
            "\1\u00d4",
            "\1\u00d6\5\uffff\1\u00d5",
            "\1\u00d8\12\uffff\1\u00d7",
            "\1\u00d9",
            "\1\u00da",
            "\1\u00db",
            "\1\u00dc",
            "\1\u00dd",
            "\1\u00de",
            "\1\u00df",
            "\1\u00e0",
            "\1\167\12\uffff\12\u00e1\52\uffff\1\174",
            "\1\167\12\uffff\12\u0091\52\uffff\1\174",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00e3",
            "\1\u00e4",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00e6",
            "\1\u00e7",
            "\1\u00e8",
            "\1\u00e9",
            "\1\u00ea",
            "\1\u00eb",
            "\1\u00ec",
            "\1\u00ee\7\uffff\1\u00ed",
            "\1\u00ef",
            "\1\u00f0",
            "\1\u00f1",
            "\1\u00f2",
            "\1\u00f3",
            "\1\u00f4",
            "\1\u00f5",
            "\1\u00f6",
            "\12\50\7\uffff\13\50\1\u00f7\16\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\1\u00f9",
            "\1\u00fa",
            "\1\u00fb",
            "\1\u00fc",
            "\1\u00fd",
            "\1\u00fe",
            "\1\u00ff\5\uffff\1\u0100",
            "\1\u0101",
            "\1\u0102",
            "\1\u0103",
            "\1\u0104",
            "\1\u0105",
            "\1\u0106",
            "\1\u0107",
            "\1\u0108",
            "\1\u0109",
            "\1\u010a",
            "\1\u010b",
            "\1\u010d\45\uffff\1\u010c",
            "\1\u010e",
            "\1\u010f",
            "\1\u0110",
            "\1\u0111",
            "\1\u0112",
            "\1\u0113",
            "\1\u0114",
            "\1\u0115",
            "\1\u0116",
            "\1\u0117",
            "\1\u0118",
            "\1\u0119",
            "\1\u011a",
            "\1\u011b",
            "\1\u011c",
            "\1\u011d",
            "\1\u011e",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0120",
            "\1\u0121",
            "\1\u0122",
            "\1\167\12\uffff\12\u00cf\52\uffff\1\174",
            "\1\u0123",
            "\1\u0124",
            "\1\u0125",
            "\1\u0126",
            "\1\u0127",
            "\1\u0128",
            "\1\u0129",
            "\1\u012a",
            "\1\u012b",
            "\1\u012c",
            "\1\u012d",
            "\1\u012e",
            "\1\u012f",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0131",
            "\1\u0132",
            "\1\u0133",
            "\1\167\12\uffff\12\u00e1\52\uffff\1\174",
            "",
            "\1\u0134",
            "\1\u0136\3\uffff\1\u0135",
            "",
            "\1\u0137",
            "\1\u0138",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u013a",
            "\1\u013b",
            "\1\u013c",
            "\1\u013d",
            "\1\u013e",
            "\1\u013f",
            "\1\u0140",
            "\1\u0141",
            "\1\u0142",
            "\1\u0143",
            "\1\u0144",
            "\1\u0145",
            "\1\u0146",
            "\1\u0147",
            "\1\u0148",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u014a",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u014c",
            "\1\u014d",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u014f\6\uffff\1\u0150\10\uffff\1\u0151\3\uffff\1\u0152",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0154",
            "\1\u0155",
            "\1\u0156",
            "\1\u0157",
            "\1\u0158",
            "\1\u0159",
            "\1\u015a",
            "\1\u015b",
            "\1\u015c",
            "\1\u015d",
            "\1\u015e",
            "\1\u015f",
            "\1\u0160",
            "\1\u0161",
            "\1\u0162",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0165",
            "\1\u0166\4\uffff\1\u0167",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0169",
            "\1\u016a",
            "\1\u016b",
            "\1\u016c",
            "\12\50\7\uffff\3\50\1\u016d\26\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u016f",
            "\1\u0170",
            "\1\u0171",
            "\1\u0172",
            "\1\u0173",
            "",
            "\1\u0174\2\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0177",
            "\1\u0178",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u017b",
            "\1\u017c",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u017f\4\uffff\1\u017e",
            "\1\u0180",
            "\1\u0181",
            "\1\u0182",
            "\1\u0183",
            "\1\u0184",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u0186",
            "\1\u0187",
            "\1\u0188",
            "\1\u018a\56\uffff\1\u0189",
            "\1\u018b",
            "\1\u018c",
            "\1\u018d",
            "\1\u018e",
            "",
            "\1\u018f",
            "\1\u0190",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0192",
            "\1\u0193",
            "\1\u0194",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0197",
            "\1\u0198",
            "\1\u0199",
            "\1\u019a",
            "\1\u019b",
            "\1\u019c",
            "\1\u019d",
            "",
            "\1\u019e",
            "",
            "\1\u019f",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u01a1",
            "\1\u01a2",
            "\1\u01a3",
            "\1\u01a4",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u01a7\3\uffff\1\u01a6",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u01a9",
            "\1\u01aa",
            "\1\u01ab",
            "\1\u01ac",
            "\1\u01ad",
            "\1\u01ae",
            "\1\u01af",
            "\1\u01b0",
            "\1\u01b1",
            "\1\u01b2",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u01b4",
            "",
            "",
            "\1\u01b5",
            "\1\u01b6",
            "\1\u01b7",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u01b8",
            "\1\u01b9",
            "\1\u01ba",
            "\1\u01bb",
            "",
            "\1\u01bc",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u01be",
            "\1\u01bf",
            "\1\u01c0",
            "\1\u01c1",
            "",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "",
            "\1\u01c4",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u01c6",
            "\1\u01c7",
            "\1\u01c8",
            "\1\u01c9",
            "\1\u01ca",
            "\1\u01cb",
            "\1\u01cc",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u01ce",
            "\1\u01cf",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u01d1",
            "\1\u01d2",
            "\1\u01d3",
            "\1\u01d4",
            "\1\u01d5",
            "\12\50\7\uffff\2\50\1\u01d6\27\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u01da",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u01de",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u01e0",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u01e2",
            "\12\50\7\uffff\21\50\1\u01e3\10\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\1\u01e5",
            "",
            "\1\u01e6",
            "\1\u01e7",
            "\1\u01e8",
            "\1\u01e9",
            "",
            "\1\u01ea",
            "\1\u01eb",
            "",
            "\1\u01ec",
            "\1\u01ed",
            "\1\u01ee",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u01f0",
            "\1\u01f1",
            "\1\u01f2",
            "\1\u01f3",
            "\1\u01f4",
            "\1\u01f5",
            "",
            "\1\u01f6",
            "\1\u01f7",
            "\1\u01f8",
            "\1\u01f9",
            "\1\u01fa",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u01fc",
            "\1\u01fd",
            "\1\u01fe",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0200",
            "\12\50\7\uffff\2\50\1\u0201\27\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0203",
            "",
            "",
            "\1\u0204",
            "",
            "\1\u0205",
            "\1\u0206",
            "\1\u0207",
            "\1\u0208",
            "\1\u0209",
            "\1\u020a",
            "\1\u020b",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u020d",
            "",
            "\1\u020e",
            "\1\u020f",
            "\1\u0210",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0213",
            "",
            "",
            "",
            "\1\u0214",
            "",
            "",
            "",
            "\1\u0215",
            "",
            "\1\u0216",
            "",
            "\1\u0217",
            "\1\u0218",
            "",
            "\1\u0219",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u021b",
            "\1\u021c",
            "\1\u021d",
            "\1\u021e",
            "\1\u021f",
            "\1\u0220",
            "\1\u0221",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u0223",
            "\1\u0224",
            "\1\u0225",
            "\1\u0226",
            "\1\u0228\16\uffff\1\u0227",
            "\1\u022a\16\uffff\1\u0229",
            "\1\u022c\16\uffff\1\u022b",
            "\1\u022d",
            "\1\u022e",
            "\1\u022f",
            "\1\u0230",
            "",
            "\1\u0231",
            "\1\u0232",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u0234",
            "\1\u0235",
            "",
            "\1\u0236",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0238",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u023a",
            "\1\u023b",
            "\1\u023d\1\uffff\1\u023c",
            "\1\u023e",
            "\1\u023f",
            "",
            "\1\u0240",
            "\1\u0241",
            "\1\u0242",
            "\1\u0243",
            "",
            "",
            "\1\u0244",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0246",
            "\1\u0247",
            "\1\u0248",
            "\1\u0249",
            "\1\u024a",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u024c",
            "\1\u024d",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u024f",
            "\1\u0250",
            "\1\u0251",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0253",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0256",
            "\1\u0257",
            "\1\u0258",
            "\1\u0259",
            "\1\u025a",
            "\1\u025b",
            "\1\u025c",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u025f",
            "\1\u0260\13\uffff\1\u0261",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u0263",
            "\1\u0264",
            "\1\u0265",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u0268",
            "\1\u0269",
            "\1\u026a",
            "\1\u026b",
            "\1\u026c",
            "\1\u026d",
            "\1\u026e",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0270",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0272",
            "",
            "\1\u0273",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0275",
            "\1\u0276",
            "\1\u0277",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u027a",
            "\12\50\7\uffff\17\50\1\u027b\12\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\1\u027d",
            "",
            "\1\u027e",
            "",
            "",
            "\1\u027f",
            "\1\u0280",
            "\1\u0281",
            "\1\u0282",
            "\1\u0283",
            "\1\u0284",
            "\1\u0285",
            "",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0287",
            "\1\u0288",
            "",
            "\1\u0289",
            "\1\u028a",
            "",
            "",
            "",
            "\12\50\7\uffff\13\50\1\u028b\16\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u028e",
            "\1\u028f",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0291",
            "\1\u0292",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u0294",
            "\1\u0295\13\uffff\1\u0296",
            "",
            "\1\u0297",
            "\1\u0298",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "",
            "\1\u029a",
            "\1\u029b",
            "",
            "\1\u029c",
            "\1\u029d",
            "\1\u029e",
            "\1\u029f",
            "\1\u02a0",
            "\1\u02a1",
            "\1\u02a2",
            "\1\u02a3",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u02a5",
            "\1\u02a6",
            "\1\u02a7",
            "\1\u02a8",
            "\1\u02a9",
            "",
            "",
            "\1\u02aa",
            "\1\u02ab",
            "",
            "\1\u02ac",
            "\1\u02ad",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u02af",
            "\1\u02b0",
            "\1\u02b1",
            "\1\u02b2",
            "",
            "\1\u02b3",
            "\1\u02b4",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u02b7",
            "\1\u02b8",
            "\1\u02b9",
            "\1\u02ba",
            "\1\u02bb",
            "\1\u02bc",
            "",
            "\1\u02bd",
            "\1\u02be",
            "\1\u02bf",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u02c1",
            "\1\u02c2",
            "\1\u02c3",
            "\1\u02c4",
            "\1\u02c5",
            "",
            "\1\u02c6",
            "\1\u02c7",
            "\1\u02c8",
            "\1\u02c9",
            "\1\u02ca",
            "\1\u02cb",
            "",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u02cd",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u02cf",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u02d1",
            "\1\u02d2",
            "\1\u02d3",
            "\1\u02d4",
            "",
            "\1\u02d5",
            "\1\u02d6",
            "\1\u02d7",
            "\1\u02d8",
            "\1\u02d9",
            "\1\u02da",
            "\1\u02db",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u02dd",
            "\1\u02de",
            "\1\u02df",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u02e3",
            "\1\u02e4",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u02e6",
            "\1\u02e7",
            "\1\u02e8",
            "\1\u02e9",
            "\1\u02ea",
            "\1\u02eb",
            "\1\u02ec",
            "",
            "\1\u02ed",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "",
            "",
            "\1\u02f0",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u02f2",
            "\1\u02f3",
            "\1\u02f4",
            "\1\u02f5",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u02f7",
            "\1\u02f8",
            "\1\u02f9",
            "",
            "",
            "\1\u02fa",
            "",
            "\1\u02fb",
            "\1\u02fc",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u02ff",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0301",
            "\1\u0302",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "",
            "\1\u0305",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "",
            "\1\u0308",
            "",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            ""
    };

    static final short[] DFA52_eot = DFA.unpackEncodedString(DFA52_eotS);
    static final short[] DFA52_eof = DFA.unpackEncodedString(DFA52_eofS);
    static final char[] DFA52_min = DFA.unpackEncodedStringToUnsignedChars(DFA52_minS);
    static final char[] DFA52_max = DFA.unpackEncodedStringToUnsignedChars(DFA52_maxS);
    static final short[] DFA52_accept = DFA.unpackEncodedString(DFA52_acceptS);
    static final short[] DFA52_special = DFA.unpackEncodedString(DFA52_specialS);
    static final short[][] DFA52_transition;

    static {
        int numStates = DFA52_transitionS.length;
        DFA52_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA52_transition[i] = DFA.unpackEncodedString(DFA52_transitionS[i]);
        }
    }

    class DFA52 extends DFA {

        public DFA52(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 52;
            this.eot = DFA52_eot;
            this.eof = DFA52_eof;
            this.min = DFA52_min;
            this.max = DFA52_max;
            this.accept = DFA52_accept;
            this.special = DFA52_special;
            this.transition = DFA52_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | T__112 | T__113 | T__114 | T__115 | T__116 | T__117 | T__118 | T__119 | T__120 | T__121 | T__122 | T__123 | T__124 | T__125 | T__126 | T__127 | T__128 | T__129 | T__130 | T__131 | T__132 | T__133 | T__134 | T__135 | T__136 | T__137 | RULE_BOOLEAN | RULE_RED | RULE_GREEN | RULE_BLUE | RULE_ALPHA | RULE_FSIZE | RULE_DEGREES | RULE_PERCENT | RULE_TFLOAT | RULE_NATURAL | RULE_ID | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS );";
        }
    }
 

}