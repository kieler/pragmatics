package de.cau.cs.kieler.kiml.graphviz.dot.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalGraphvizDotLexer extends Lexer {
    public static final int RULE_ID=4;
    public static final int RULE_ANY_OTHER=12;
    public static final int T29=29;
    public static final int RULE_PREC_LINE=8;
    public static final int T28=28;
    public static final int T27=27;
    public static final int T26=26;
    public static final int T25=25;
    public static final int RULE_FLOAT=6;
    public static final int Tokens=39;
    public static final int T24=24;
    public static final int EOF=-1;
    public static final int RULE_SL_COMMENT=10;
    public static final int T23=23;
    public static final int T22=22;
    public static final int T21=21;
    public static final int T20=20;
    public static final int RULE_ML_COMMENT=9;
    public static final int RULE_STRING=7;
    public static final int RULE_INT=5;
    public static final int T38=38;
    public static final int T37=37;
    public static final int T13=13;
    public static final int T14=14;
    public static final int T34=34;
    public static final int RULE_WS=11;
    public static final int T15=15;
    public static final int T33=33;
    public static final int T16=16;
    public static final int T36=36;
    public static final int T17=17;
    public static final int T35=35;
    public static final int T18=18;
    public static final int T30=30;
    public static final int T19=19;
    public static final int T32=32;
    public static final int T31=31;
    public InternalGraphvizDotLexer() {;} 
    public InternalGraphvizDotLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g"; }

    // $ANTLR start T13
    public final void mT13() throws RecognitionException {
        try {
            int _type = T13;
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:10:5: ( 'strict' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:10:7: 'strict'
            {
            match("strict"); 


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
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:11:5: ( '{' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:11:7: '{'
            {
            match('{'); 

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
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:12:5: ( '}' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:12:7: '}'
            {
            match('}'); 

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
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:13:5: ( ';' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:13:7: ';'
            {
            match(';'); 

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
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:14:5: ( '[' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:14:7: '['
            {
            match('['); 

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
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:15:5: ( ',' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:15:7: ','
            {
            match(','); 

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
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:16:5: ( ']' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:16:7: ']'
            {
            match(']'); 

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
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:17:5: ( 'subgraph' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:17:7: 'subgraph'
            {
            match("subgraph"); 


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
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:18:5: ( '=' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:18:7: '='
            {
            match('='); 

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
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:19:5: ( ':' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:19:7: ':'
            {
            match(':'); 

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
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:20:5: ( '->' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:20:7: '->'
            {
            match("->"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T23

    // $ANTLR start T24
    public final void mT24() throws RecognitionException {
        try {
            int _type = T24;
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:21:5: ( '--' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:21:7: '--'
            {
            match("--"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T24

    // $ANTLR start T25
    public final void mT25() throws RecognitionException {
        try {
            int _type = T25;
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:22:5: ( 'graph' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:22:7: 'graph'
            {
            match("graph"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T25

    // $ANTLR start T26
    public final void mT26() throws RecognitionException {
        try {
            int _type = T26;
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:23:5: ( 'digraph' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:23:7: 'digraph'
            {
            match("digraph"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T26

    // $ANTLR start T27
    public final void mT27() throws RecognitionException {
        try {
            int _type = T27;
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:24:5: ( 'node' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:24:7: 'node'
            {
            match("node"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T27

    // $ANTLR start T28
    public final void mT28() throws RecognitionException {
        try {
            int _type = T28;
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:25:5: ( 'edge' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:25:7: 'edge'
            {
            match("edge"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T28

    // $ANTLR start T29
    public final void mT29() throws RecognitionException {
        try {
            int _type = T29;
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:26:5: ( 'n' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:26:7: 'n'
            {
            match('n'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T29

    // $ANTLR start T30
    public final void mT30() throws RecognitionException {
        try {
            int _type = T30;
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:27:5: ( 'ne' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:27:7: 'ne'
            {
            match("ne"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T30

    // $ANTLR start T31
    public final void mT31() throws RecognitionException {
        try {
            int _type = T31;
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:28:5: ( 'e' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:28:7: 'e'
            {
            match('e'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T31

    // $ANTLR start T32
    public final void mT32() throws RecognitionException {
        try {
            int _type = T32;
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:29:5: ( 'se' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:29:7: 'se'
            {
            match("se"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T32

    // $ANTLR start T33
    public final void mT33() throws RecognitionException {
        try {
            int _type = T33;
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:30:5: ( 's' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:30:7: 's'
            {
            match('s'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T33

    // $ANTLR start T34
    public final void mT34() throws RecognitionException {
        try {
            int _type = T34;
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:31:5: ( 'sw' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:31:7: 'sw'
            {
            match("sw"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T34

    // $ANTLR start T35
    public final void mT35() throws RecognitionException {
        try {
            int _type = T35;
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:32:5: ( 'w' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:32:7: 'w'
            {
            match('w'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T35

    // $ANTLR start T36
    public final void mT36() throws RecognitionException {
        try {
            int _type = T36;
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:33:5: ( 'nw' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:33:7: 'nw'
            {
            match("nw"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T36

    // $ANTLR start T37
    public final void mT37() throws RecognitionException {
        try {
            int _type = T37;
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:34:5: ( 'c' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:34:7: 'c'
            {
            match('c'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T37

    // $ANTLR start T38
    public final void mT38() throws RecognitionException {
        try {
            int _type = T38;
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:35:5: ( '_' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:35:7: '_'
            {
            match('_'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T38

    // $ANTLR start RULE_INT
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1376:10: ( ( '-' )? ( '0' .. '9' )+ )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1376:12: ( '-' )? ( '0' .. '9' )+
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1376:12: ( '-' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='-') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1376:12: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1376:17: ( '0' .. '9' )+
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
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1376:18: '0' .. '9'
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

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_INT

    // $ANTLR start RULE_ID
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1378:9: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1378:11: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1378:35: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')||(LA3_0>='A' && LA3_0<='Z')||LA3_0=='_'||(LA3_0>='a' && LA3_0<='z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:
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
            	    break loop3;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ID

    // $ANTLR start RULE_FLOAT
    public final void mRULE_FLOAT() throws RecognitionException {
        try {
            int _type = RULE_FLOAT;
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1380:12: ( ( '-' )? ( '0' .. '9' )* '.' ( '0' .. '9' )+ )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1380:14: ( '-' )? ( '0' .. '9' )* '.' ( '0' .. '9' )+
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1380:14: ( '-' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='-') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1380:14: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1380:19: ( '0' .. '9' )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1380:20: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            match('.'); 
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1380:35: ( '0' .. '9' )+
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
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1380:36: '0' .. '9'
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


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_FLOAT

    // $ANTLR start RULE_STRING
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1382:13: ( '\"' ( '\\\\' '\"' | ~ ( '\"' ) )* '\"' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1382:15: '\"' ( '\\\\' '\"' | ~ ( '\"' ) )* '\"'
            {
            match('\"'); 
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1382:19: ( '\\\\' '\"' | ~ ( '\"' ) )*
            loop7:
            do {
                int alt7=3;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='\\') ) {
                    int LA7_2 = input.LA(2);

                    if ( (LA7_2=='\"') ) {
                        int LA7_4 = input.LA(3);

                        if ( ((LA7_4>='\u0000' && LA7_4<='\uFFFE')) ) {
                            alt7=1;
                        }

                        else {
                            alt7=2;
                        }

                    }
                    else if ( ((LA7_2>='\u0000' && LA7_2<='!')||(LA7_2>='#' && LA7_2<='\uFFFE')) ) {
                        alt7=2;
                    }


                }
                else if ( ((LA7_0>='\u0000' && LA7_0<='!')||(LA7_0>='#' && LA7_0<='[')||(LA7_0>=']' && LA7_0<='\uFFFE')) ) {
                    alt7=2;
                }


                switch (alt7) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1382:20: '\\\\' '\"'
            	    {
            	    match('\\'); 
            	    match('\"'); 

            	    }
            	    break;
            	case 2 :
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1382:29: ~ ( '\"' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='\uFFFE') ) {
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
            	    break loop7;
                }
            } while (true);

            match('\"'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_STRING

    // $ANTLR start RULE_PREC_LINE
    public final void mRULE_PREC_LINE() throws RecognitionException {
        try {
            int _type = RULE_PREC_LINE;
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1384:16: ( '#' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1384:18: '#' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match('#'); 
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1384:22: (~ ( ( '\\n' | '\\r' ) ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='\u0000' && LA8_0<='\t')||(LA8_0>='\u000B' && LA8_0<='\f')||(LA8_0>='\u000E' && LA8_0<='\uFFFE')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1384:22: ~ ( ( '\\n' | '\\r' ) )
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

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1384:38: ( ( '\\r' )? '\\n' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='\n'||LA10_0=='\r') ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1384:39: ( '\\r' )? '\\n'
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1384:39: ( '\\r' )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0=='\r') ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1384:39: '\\r'
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
    // $ANTLR end RULE_PREC_LINE

    // $ANTLR start RULE_ML_COMMENT
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1386:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1386:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1386:24: ( options {greedy=false; } : . )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0=='*') ) {
                    int LA11_1 = input.LA(2);

                    if ( (LA11_1=='/') ) {
                        alt11=2;
                    }
                    else if ( ((LA11_1>='\u0000' && LA11_1<='.')||(LA11_1>='0' && LA11_1<='\uFFFE')) ) {
                        alt11=1;
                    }


                }
                else if ( ((LA11_0>='\u0000' && LA11_0<=')')||(LA11_0>='+' && LA11_0<='\uFFFE')) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1386:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop11;
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
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1388:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1388:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1388:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>='\u0000' && LA12_0<='\t')||(LA12_0>='\u000B' && LA12_0<='\f')||(LA12_0>='\u000E' && LA12_0<='\uFFFE')) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1388:24: ~ ( ( '\\n' | '\\r' ) )
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
            	    break loop12;
                }
            } while (true);

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1388:40: ( ( '\\r' )? '\\n' )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0=='\n'||LA14_0=='\r') ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1388:41: ( '\\r' )? '\\n'
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1388:41: ( '\\r' )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0=='\r') ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1388:41: '\\r'
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
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1390:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1390:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1390:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
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
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:
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
            	    if ( cnt15 >= 1 ) break loop15;
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
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
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1392:16: ( . )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1392:18: .
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
        // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:8: ( T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | RULE_INT | RULE_ID | RULE_FLOAT | RULE_STRING | RULE_PREC_LINE | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt16=35;
        alt16 = dfa16.predict(input);
        switch (alt16) {
            case 1 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:10: T13
                {
                mT13(); 

                }
                break;
            case 2 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:14: T14
                {
                mT14(); 

                }
                break;
            case 3 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:18: T15
                {
                mT15(); 

                }
                break;
            case 4 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:22: T16
                {
                mT16(); 

                }
                break;
            case 5 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:26: T17
                {
                mT17(); 

                }
                break;
            case 6 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:30: T18
                {
                mT18(); 

                }
                break;
            case 7 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:34: T19
                {
                mT19(); 

                }
                break;
            case 8 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:38: T20
                {
                mT20(); 

                }
                break;
            case 9 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:42: T21
                {
                mT21(); 

                }
                break;
            case 10 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:46: T22
                {
                mT22(); 

                }
                break;
            case 11 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:50: T23
                {
                mT23(); 

                }
                break;
            case 12 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:54: T24
                {
                mT24(); 

                }
                break;
            case 13 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:58: T25
                {
                mT25(); 

                }
                break;
            case 14 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:62: T26
                {
                mT26(); 

                }
                break;
            case 15 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:66: T27
                {
                mT27(); 

                }
                break;
            case 16 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:70: T28
                {
                mT28(); 

                }
                break;
            case 17 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:74: T29
                {
                mT29(); 

                }
                break;
            case 18 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:78: T30
                {
                mT30(); 

                }
                break;
            case 19 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:82: T31
                {
                mT31(); 

                }
                break;
            case 20 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:86: T32
                {
                mT32(); 

                }
                break;
            case 21 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:90: T33
                {
                mT33(); 

                }
                break;
            case 22 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:94: T34
                {
                mT34(); 

                }
                break;
            case 23 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:98: T35
                {
                mT35(); 

                }
                break;
            case 24 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:102: T36
                {
                mT36(); 

                }
                break;
            case 25 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:106: T37
                {
                mT37(); 

                }
                break;
            case 26 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:110: T38
                {
                mT38(); 

                }
                break;
            case 27 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:114: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 28 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:123: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 29 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:131: RULE_FLOAT
                {
                mRULE_FLOAT(); 

                }
                break;
            case 30 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:142: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 31 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:154: RULE_PREC_LINE
                {
                mRULE_PREC_LINE(); 

                }
                break;
            case 32 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:169: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 33 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:185: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 34 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:201: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 35 :
                // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1:209: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA16 dfa16 = new DFA16(this);
    static final String DFA16_eotS =
        "\1\uffff\1\36\10\uffff\1\31\2\37\1\61\1\63\1\64\1\65\1\66\1\67\1"+
        "\uffff\2\31\1\uffff\1\31\2\uffff\1\75\1\76\2\37\14\uffff\1\67\1"+
        "\uffff\2\37\1\103\1\104\1\37\1\uffff\1\37\14\uffff\4\37\2\uffff"+
        "\6\37\1\121\1\122\2\37\1\125\1\37\2\uffff\1\127\1\37\1\uffff\1\37"+
        "\1\uffff\1\37\1\133\1\134\2\uffff";
    static final String DFA16_eofS =
        "\135\uffff";
    static final String DFA16_minS =
        "\1\0\1\60\10\uffff\1\55\1\162\1\151\5\60\1\56\1\uffff\1\60\1\0\1"+
        "\uffff\1\52\2\uffff\2\60\1\162\1\142\14\uffff\1\56\1\uffff\1\141"+
        "\1\147\2\60\1\144\1\uffff\1\147\14\uffff\1\151\1\147\1\160\1\162"+
        "\2\uffff\2\145\1\143\1\162\1\150\1\141\2\60\1\164\1\141\1\60\1\160"+
        "\2\uffff\1\60\1\160\1\uffff\1\150\1\uffff\1\150\2\60\2\uffff";
    static final String DFA16_maxS =
        "\1\ufffe\1\172\10\uffff\1\76\1\162\1\151\5\172\1\71\1\uffff\1\71"+
        "\1\ufffe\1\uffff\1\57\2\uffff\2\172\1\162\1\142\14\uffff\1\71\1"+
        "\uffff\1\141\1\147\2\172\1\144\1\uffff\1\147\14\uffff\1\151\1\147"+
        "\1\160\1\162\2\uffff\2\145\1\143\1\162\1\150\1\141\2\172\1\164\1"+
        "\141\1\172\1\160\2\uffff\1\172\1\160\1\uffff\1\150\1\uffff\1\150"+
        "\2\172\2\uffff";
    static final String DFA16_acceptS =
        "\2\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\11\1\12\11\uffff\1\34\2\uffff"+
        "\1\37\1\uffff\1\42\1\43\4\uffff\1\25\1\34\1\2\1\3\1\4\1\5\1\6\1"+
        "\7\1\11\1\12\1\14\1\13\1\uffff\1\35\5\uffff\1\21\1\uffff\1\23\1"+
        "\27\1\31\1\32\1\33\1\36\1\37\1\41\1\40\1\42\1\24\1\26\4\uffff\1"+
        "\30\1\22\14\uffff\1\17\1\20\2\uffff\1\15\1\uffff\1\1\3\uffff\1\16"+
        "\1\10";
    static final String DFA16_specialS =
        "\135\uffff}>";
    static final String[] DFA16_transitionS = {
            "\11\31\2\30\2\31\1\30\22\31\1\30\1\31\1\25\1\26\10\31\1\6\1"+
            "\12\1\24\1\27\12\22\1\11\1\4\1\31\1\10\3\31\32\23\1\5\1\31\1"+
            "\7\1\31\1\21\1\31\2\23\1\20\1\14\1\16\1\23\1\13\6\23\1\15\4"+
            "\23\1\1\3\23\1\17\3\23\1\2\1\31\1\3\uff81\31",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\4\37\1\32\16\37\1"+
            "\34\1\35\1\37\1\33\3\37",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\50\1\53\1\uffff\12\52\4\uffff\1\51",
            "\1\54",
            "\1\55",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\4\37\1\57\11\37\1"+
            "\60\7\37\1\56\3\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\3\37\1\62\26\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\53\1\uffff\12\52",
            "",
            "\12\53",
            "\uffff\70",
            "",
            "\1\73\4\uffff\1\72",
            "",
            "",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\77",
            "\1\100",
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
            "",
            "",
            "\1\53\1\uffff\12\52",
            "",
            "\1\101",
            "\1\102",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\105",
            "",
            "\1\106",
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
            "",
            "",
            "\1\107",
            "\1\110",
            "\1\111",
            "\1\112",
            "",
            "",
            "\1\113",
            "\1\114",
            "\1\115",
            "\1\116",
            "\1\117",
            "\1\120",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\123",
            "\1\124",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\126",
            "",
            "",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\130",
            "",
            "\1\131",
            "",
            "\1\132",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
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
            return "1:1: Tokens : ( T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | RULE_INT | RULE_ID | RULE_FLOAT | RULE_STRING | RULE_PREC_LINE | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
    }
 

}