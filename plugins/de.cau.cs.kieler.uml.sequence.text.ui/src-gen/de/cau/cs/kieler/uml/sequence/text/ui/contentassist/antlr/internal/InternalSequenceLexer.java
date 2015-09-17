package de.cau.cs.kieler.uml.sequence.text.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalSequenceLexer extends Lexer {
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int RULE_ID=5;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=7;
    public static final int T__29=29;
    public static final int RULE_INT_GREATER_ZERO=6;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=8;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=9;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_WS=10;
    public static final int RULE_ANY_OTHER=11;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators

    public InternalSequenceLexer() {;} 
    public InternalSequenceLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalSequenceLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g"; }

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:11:7: ( 'lost' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:11:9: 'lost'
            {
            match("lost"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:12:7: ( 'found' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:12:9: 'found'
            {
            match("found"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:13:7: ( 'async' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:13:9: 'async'
            {
            match("async"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:14:7: ( 'response' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:14:9: 'response'
            {
            match("response"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:15:7: ( 'sync' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:15:9: 'sync'
            {
            match("sync"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:16:7: ( 'create' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:16:9: 'create'
            {
            match("create"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:17:7: ( 'sequenceDiagram' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:17:9: 'sequenceDiagram'
            {
            match("sequenceDiagram"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:18:7: ( 'lifeline' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:18:9: 'lifeline'
            {
            match("lifeline"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:19:7: ( 'as' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:19:9: 'as'
            {
            match("as"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:20:7: ( 'usecase' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:20:9: 'usecase'
            {
            match("usecase"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:21:7: ( 'to' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:21:9: 'to'
            {
            match("to"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:22:7: ( 'sourceNote' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:22:9: 'sourceNote'
            {
            match("sourceNote"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:23:7: ( 'targetNote' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:23:9: 'targetNote'
            {
            match("targetNote"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:24:7: ( 'note' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:24:9: 'note'
            {
            match("note"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:25:7: ( 'self' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:25:9: 'self'
            {
            match("self"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:26:7: ( 'destroy' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:26:9: 'destroy'
            {
            match("destroy"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:27:7: ( 'fragment' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:27:9: 'fragment'
            {
            match("fragment"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:28:7: ( '{' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:28:9: '{'
            {
            match('{'); 

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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:29:7: ( '}' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:29:9: '}'
            {
            match('}'); 

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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:30:7: ( 'label' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:30:9: 'label'
            {
            match("label"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:31:7: ( 'refinement' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:31:9: 'refinement'
            {
            match("refinement"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:32:7: ( 'lifelines' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:32:9: 'lifelines'
            {
            match("lifelines"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:33:7: ( ',' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:33:9: ','
            {
            match(','); 

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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:34:7: ( 'sourceStartEndExec' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:34:9: 'sourceStartEndExec'
            {
            match("sourceStartEndExec"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:35:7: ( 'sourceStartExec' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:35:9: 'sourceStartExec'
            {
            match("sourceStartExec"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:36:7: ( 'sourceEndExec' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:36:9: 'sourceEndExec'
            {
            match("sourceEndExec"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:37:7: ( 'targetStartEndExec' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:37:9: 'targetStartEndExec'
            {
            match("targetStartEndExec"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:38:7: ( 'targetStartExec' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:38:9: 'targetStartExec'
            {
            match("targetStartExec"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:39:7: ( 'targetEndExec' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:39:9: 'targetEndExec'
            {
            match("targetEndExec"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:40:7: ( 'startEndExec' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:40:9: 'startEndExec'
            {
            match("startEndExec"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:41:7: ( 'startExec' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:41:9: 'startExec'
            {
            match("startExec"); 


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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:42:7: ( 'endExec' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:42:9: 'endExec'
            {
            match("endExec"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "RULE_INT_GREATER_ZERO"
    public final void mRULE_INT_GREATER_ZERO() throws RecognitionException {
        try {
            int _type = RULE_INT_GREATER_ZERO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3887:23: ( ( '1' .. '9' ( '0' .. '9' )* | 'all' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3887:25: ( '1' .. '9' ( '0' .. '9' )* | 'all' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3887:25: ( '1' .. '9' ( '0' .. '9' )* | 'all' )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( ((LA2_0>='1' && LA2_0<='9')) ) {
                alt2=1;
            }
            else if ( (LA2_0=='a') ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3887:26: '1' .. '9' ( '0' .. '9' )*
                    {
                    matchRange('1','9'); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3887:35: ( '0' .. '9' )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( ((LA1_0>='0' && LA1_0<='9')) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3887:36: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3887:47: 'all'
                    {
                    match("all"); 


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
    // $ANTLR end "RULE_INT_GREATER_ZERO"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3889:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3889:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3889:11: ( '^' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='^') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3889:11: '^'
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

            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3889:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')||(LA4_0>='A' && LA4_0<='Z')||LA4_0=='_'||(LA4_0>='a' && LA4_0<='z')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:
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
            	    break loop4;
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
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3891:10: ( ( '0' .. '9' )+ )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3891:12: ( '0' .. '9' )+
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3891:12: ( '0' .. '9' )+
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
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3891:13: '0' .. '9'
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

            state.type = _type;
            state.channel = _channel;
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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3893:13: ( ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3893:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3893:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0=='\"') ) {
                alt8=1;
            }
            else if ( (LA8_0=='\'') ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3893:16: '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3893:20: ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop6:
                    do {
                        int alt6=3;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0=='\\') ) {
                            alt6=1;
                        }
                        else if ( ((LA6_0>='\u0000' && LA6_0<='!')||(LA6_0>='#' && LA6_0<='[')||(LA6_0>=']' && LA6_0<='\uFFFF')) ) {
                            alt6=2;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3893:21: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3893:28: ~ ( ( '\\\\' | '\"' ) )
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
                    	    break loop6;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3893:48: '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3893:53: ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop7:
                    do {
                        int alt7=3;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0=='\\') ) {
                            alt7=1;
                        }
                        else if ( ((LA7_0>='\u0000' && LA7_0<='&')||(LA7_0>='(' && LA7_0<='[')||(LA7_0>=']' && LA7_0<='\uFFFF')) ) {
                            alt7=2;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3893:54: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3893:61: ~ ( ( '\\\\' | '\\'' ) )
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
                    	    break loop7;
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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3895:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3895:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3895:24: ( options {greedy=false; } : . )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0=='*') ) {
                    int LA9_1 = input.LA(2);

                    if ( (LA9_1=='/') ) {
                        alt9=2;
                    }
                    else if ( ((LA9_1>='\u0000' && LA9_1<='.')||(LA9_1>='0' && LA9_1<='\uFFFF')) ) {
                        alt9=1;
                    }


                }
                else if ( ((LA9_0>='\u0000' && LA9_0<=')')||(LA9_0>='+' && LA9_0<='\uFFFF')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3895:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop9;
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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3897:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3897:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3897:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='\u0000' && LA10_0<='\t')||(LA10_0>='\u000B' && LA10_0<='\f')||(LA10_0>='\u000E' && LA10_0<='\uFFFF')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3897:24: ~ ( ( '\\n' | '\\r' ) )
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
            	    break loop10;
                }
            } while (true);

            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3897:40: ( ( '\\r' )? '\\n' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0=='\n'||LA12_0=='\r') ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3897:41: ( '\\r' )? '\\n'
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3897:41: ( '\\r' )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0=='\r') ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3897:41: '\\r'
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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3899:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3899:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3899:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>='\t' && LA13_0<='\n')||LA13_0=='\r'||LA13_0==' ') ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:
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
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
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
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3901:16: ( . )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3901:18: .
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
        // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:8: ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | RULE_INT_GREATER_ZERO | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt14=40;
        alt14 = dfa14.predict(input);
        switch (alt14) {
            case 1 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:10: T__12
                {
                mT__12(); 

                }
                break;
            case 2 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:16: T__13
                {
                mT__13(); 

                }
                break;
            case 3 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:22: T__14
                {
                mT__14(); 

                }
                break;
            case 4 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:28: T__15
                {
                mT__15(); 

                }
                break;
            case 5 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:34: T__16
                {
                mT__16(); 

                }
                break;
            case 6 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:40: T__17
                {
                mT__17(); 

                }
                break;
            case 7 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:46: T__18
                {
                mT__18(); 

                }
                break;
            case 8 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:52: T__19
                {
                mT__19(); 

                }
                break;
            case 9 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:58: T__20
                {
                mT__20(); 

                }
                break;
            case 10 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:64: T__21
                {
                mT__21(); 

                }
                break;
            case 11 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:70: T__22
                {
                mT__22(); 

                }
                break;
            case 12 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:76: T__23
                {
                mT__23(); 

                }
                break;
            case 13 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:82: T__24
                {
                mT__24(); 

                }
                break;
            case 14 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:88: T__25
                {
                mT__25(); 

                }
                break;
            case 15 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:94: T__26
                {
                mT__26(); 

                }
                break;
            case 16 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:100: T__27
                {
                mT__27(); 

                }
                break;
            case 17 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:106: T__28
                {
                mT__28(); 

                }
                break;
            case 18 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:112: T__29
                {
                mT__29(); 

                }
                break;
            case 19 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:118: T__30
                {
                mT__30(); 

                }
                break;
            case 20 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:124: T__31
                {
                mT__31(); 

                }
                break;
            case 21 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:130: T__32
                {
                mT__32(); 

                }
                break;
            case 22 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:136: T__33
                {
                mT__33(); 

                }
                break;
            case 23 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:142: T__34
                {
                mT__34(); 

                }
                break;
            case 24 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:148: T__35
                {
                mT__35(); 

                }
                break;
            case 25 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:154: T__36
                {
                mT__36(); 

                }
                break;
            case 26 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:160: T__37
                {
                mT__37(); 

                }
                break;
            case 27 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:166: T__38
                {
                mT__38(); 

                }
                break;
            case 28 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:172: T__39
                {
                mT__39(); 

                }
                break;
            case 29 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:178: T__40
                {
                mT__40(); 

                }
                break;
            case 30 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:184: T__41
                {
                mT__41(); 

                }
                break;
            case 31 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:190: T__42
                {
                mT__42(); 

                }
                break;
            case 32 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:196: T__43
                {
                mT__43(); 

                }
                break;
            case 33 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:202: RULE_INT_GREATER_ZERO
                {
                mRULE_INT_GREATER_ZERO(); 

                }
                break;
            case 34 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:224: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 35 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:232: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 36 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:241: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 37 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:253: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 38 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:269: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 39 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:285: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 40 :
                // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1:293: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA14 dfa14 = new DFA14(this);
    static final String DFA14_eotS =
        "\1\uffff\12\33\3\uffff\1\33\1\60\1\27\2\uffff\3\27\2\uffff\3\33\1\uffff\2\33\1\74\10\33\1\107\3\33\3\uffff\1\33\1\60\6\uffff\6\33\1\uffff\1\60\11\33\1\uffff\4\33\1\137\7\33\1\147\1\33\1\151\5\33\1\157\2\33\1\uffff\1\33\1\163\1\164\1\33\1\166\2\33\1\uffff\1\33\1\uffff\5\33\1\uffff\3\33\2\uffff\1\33\1\uffff\5\33\1\u008b\16\33\1\uffff\1\u009c\3\33\1\u00a0\1\u00a1\1\u00a3\1\u00a4\1\u00a5\7\33\1\uffff\3\33\2\uffff\1\u00b0\3\uffff\6\33\1\u00b7\3\33\1\uffff\1\u00bb\1\33\1\u00bd\3\33\1\uffff\1\u00c1\2\33\1\uffff\1\33\1\uffff\3\33\1\uffff\5\33\1\u00ce\5\33\1\u00d5\1\uffff\2\33\1\u00d8\3\33\1\uffff\2\33\1\uffff\1\u00de\1\33\1\u00e0\1\33\1\u00e2\1\uffff\1\33\1\uffff\1\33\1\uffff\2\33\1\u00e7\1\u00e8\2\uffff";
    static final String DFA14_eofS =
        "\u00e9\uffff";
    static final String DFA14_minS =
        "\1\0\1\141\1\157\1\154\2\145\1\162\1\163\1\141\1\157\1\145\3\uffff\1\156\1\60\1\101\2\uffff\2\0\1\52\2\uffff\1\163\1\146\1\142\1\uffff\1\165\1\141\1\60\1\154\1\146\1\156\1\154\1\165\1\141\2\145\1\60\1\162\1\164\1\163\3\uffff\1\144\1\60\6\uffff\1\164\2\145\1\156\1\147\1\156\1\uffff\1\60\1\160\1\151\1\143\1\165\1\146\2\162\1\141\1\143\1\uffff\1\147\1\145\1\164\1\105\1\60\2\154\1\144\1\155\1\143\1\157\1\156\1\60\1\145\1\60\1\143\2\164\1\141\1\145\1\60\1\162\1\170\1\uffff\1\151\2\60\1\145\1\60\1\156\1\145\1\uffff\1\156\1\uffff\1\145\1\105\1\145\1\163\1\164\1\uffff\1\157\1\145\1\156\2\uffff\1\156\1\uffff\1\163\1\155\1\143\1\105\1\156\1\60\1\145\1\105\1\171\1\143\1\145\1\164\3\145\1\157\1\164\1\156\1\144\1\145\1\uffff\1\60\1\157\1\164\1\156\5\60\1\156\1\104\1\164\1\141\1\144\1\105\1\143\1\uffff\1\164\1\141\1\144\2\uffff\1\60\3\uffff\1\164\1\151\1\145\1\162\1\105\1\170\1\60\1\145\1\162\1\105\1\uffff\1\60\1\141\1\60\1\164\1\170\1\145\1\uffff\1\60\1\164\1\170\1\uffff\1\147\1\uffff\1\105\1\145\1\143\1\uffff\1\105\1\145\1\162\1\156\1\143\1\60\1\156\1\143\1\141\1\144\1\145\1\60\1\uffff\1\144\1\145\1\60\1\155\1\105\1\143\1\uffff\1\105\1\143\1\uffff\1\60\1\170\1\60\1\170\1\60\1\uffff\1\145\1\uffff\1\145\1\uffff\2\143\2\60\2\uffff";
    static final String DFA14_maxS =
        "\1\uffff\1\157\1\162\1\163\1\145\1\171\1\162\1\163\2\157\1\145\3\uffff\1\156\1\71\1\172\2\uffff\2\uffff\1\57\2\uffff\1\163\1\146\1\142\1\uffff\1\165\1\141\1\172\1\154\1\163\1\156\1\161\1\165\1\141\2\145\1\172\1\162\1\164\1\163\3\uffff\1\144\1\71\6\uffff\1\164\2\145\1\156\1\147\1\156\1\uffff\1\172\1\160\1\151\1\143\1\165\1\146\2\162\1\141\1\143\1\uffff\1\147\1\145\1\164\1\105\1\172\2\154\1\144\1\155\1\143\1\157\1\156\1\172\1\145\1\172\1\143\2\164\1\141\1\145\1\172\1\162\1\170\1\uffff\1\151\2\172\1\145\1\172\1\156\1\145\1\uffff\1\156\1\uffff\1\145\1\105\1\145\1\163\1\164\1\uffff\1\157\1\145\1\156\2\uffff\1\156\1\uffff\1\163\1\155\1\143\1\123\1\170\1\172\1\145\1\123\1\171\1\143\1\145\1\164\3\145\1\157\1\164\1\156\1\144\1\145\1\uffff\1\172\1\157\1\164\1\156\5\172\1\156\1\104\1\164\1\141\1\144\1\105\1\143\1\uffff\1\164\1\141\1\144\2\uffff\1\172\3\uffff\1\164\1\151\1\145\1\162\1\105\1\170\1\172\1\145\1\162\1\105\1\uffff\1\172\1\141\1\172\1\164\1\170\1\145\1\uffff\1\172\1\164\1\170\1\uffff\1\147\1\uffff\1\105\1\145\1\143\1\uffff\1\105\1\145\1\162\1\170\1\143\1\172\1\170\1\143\1\141\1\144\1\145\1\172\1\uffff\1\144\1\145\1\172\1\155\1\105\1\143\1\uffff\1\105\1\143\1\uffff\1\172\1\170\1\172\1\170\1\172\1\uffff\1\145\1\uffff\1\145\1\uffff\2\143\2\172\2\uffff";
    static final String DFA14_acceptS =
        "\13\uffff\1\22\1\23\1\27\3\uffff\1\42\1\43\3\uffff\1\47\1\50\3\uffff\1\42\17\uffff\1\22\1\23\1\27\2\uffff\1\41\1\43\1\44\1\45\1\46\1\47\6\uffff\1\11\12\uffff\1\13\27\uffff\1\1\7\uffff\1\5\1\uffff\1\17\5\uffff\1\16\3\uffff\1\24\1\2\1\uffff\1\3\24\uffff\1\6\20\uffff\1\12\3\uffff\1\20\1\40\1\uffff\1\10\1\21\1\4\12\uffff\1\26\6\uffff\1\37\3\uffff\1\25\1\uffff\1\14\3\uffff\1\15\14\uffff\1\36\6\uffff\1\32\2\uffff\1\35\5\uffff\1\7\1\uffff\1\31\1\uffff\1\34\4\uffff\1\30\1\33";
    static final String DFA14_specialS =
        "\1\0\22\uffff\1\1\1\2\u00d4\uffff}>";
    static final String[] DFA14_transitionS = {
            "\11\27\2\26\2\27\1\26\22\27\1\26\1\27\1\23\4\27\1\24\4\27\1\15\2\27\1\25\1\22\11\17\7\27\32\21\3\27\1\20\1\21\1\27\1\3\1\21\1\6\1\12\1\16\1\2\5\21\1\1\1\21\1\11\3\21\1\4\1\5\1\10\1\7\5\21\1\13\1\27\1\14\uff82\27",
            "\1\32\7\uffff\1\31\5\uffff\1\30",
            "\1\34\2\uffff\1\35",
            "\1\37\6\uffff\1\36",
            "\1\40",
            "\1\42\11\uffff\1\43\4\uffff\1\44\4\uffff\1\41",
            "\1\45",
            "\1\46",
            "\1\50\15\uffff\1\47",
            "\1\51",
            "\1\52",
            "",
            "",
            "",
            "\1\56",
            "\12\57",
            "\32\33\4\uffff\1\33\1\uffff\32\33",
            "",
            "",
            "\0\62",
            "\0\62",
            "\1\63\4\uffff\1\64",
            "",
            "",
            "\1\66",
            "\1\67",
            "\1\70",
            "",
            "\1\71",
            "\1\72",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\30\33\1\73\1\33",
            "\1\75",
            "\1\77\14\uffff\1\76",
            "\1\100",
            "\1\102\4\uffff\1\101",
            "\1\103",
            "\1\104",
            "\1\105",
            "\1\106",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\110",
            "\1\111",
            "\1\112",
            "",
            "",
            "",
            "\1\113",
            "\12\57",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\114",
            "\1\115",
            "\1\116",
            "\1\117",
            "\1\120",
            "\1\121",
            "",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\122",
            "\1\123",
            "\1\124",
            "\1\125",
            "\1\126",
            "\1\127",
            "\1\130",
            "\1\131",
            "\1\132",
            "",
            "\1\133",
            "\1\134",
            "\1\135",
            "\1\136",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\140",
            "\1\141",
            "\1\142",
            "\1\143",
            "\1\144",
            "\1\145",
            "\1\146",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\150",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\152",
            "\1\153",
            "\1\154",
            "\1\155",
            "\1\156",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\160",
            "\1\161",
            "",
            "\1\162",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\165",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\167",
            "\1\170",
            "",
            "\1\171",
            "",
            "\1\172",
            "\1\173",
            "\1\174",
            "\1\175",
            "\1\176",
            "",
            "\1\177",
            "\1\u0080",
            "\1\u0081",
            "",
            "",
            "\1\u0082",
            "",
            "\1\u0083",
            "\1\u0084",
            "\1\u0085",
            "\1\u0088\10\uffff\1\u0086\4\uffff\1\u0087",
            "\1\u0089\11\uffff\1\u008a",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\u008c",
            "\1\u008f\10\uffff\1\u008d\4\uffff\1\u008e",
            "\1\u0090",
            "\1\u0091",
            "\1\u0092",
            "\1\u0093",
            "\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "\1\u0097",
            "\1\u0098",
            "\1\u0099",
            "\1\u009a",
            "\1\u009b",
            "",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\22\33\1\u00a2\7\33",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\u00a6",
            "\1\u00a7",
            "\1\u00a8",
            "\1\u00a9",
            "\1\u00aa",
            "\1\u00ab",
            "\1\u00ac",
            "",
            "\1\u00ad",
            "\1\u00ae",
            "\1\u00af",
            "",
            "",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "",
            "",
            "",
            "\1\u00b1",
            "\1\u00b2",
            "\1\u00b3",
            "\1\u00b4",
            "\1\u00b5",
            "\1\u00b6",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\u00b8",
            "\1\u00b9",
            "\1\u00ba",
            "",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\u00bc",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\u00c2",
            "\1\u00c3",
            "",
            "\1\u00c4",
            "",
            "\1\u00c5",
            "\1\u00c6",
            "\1\u00c7",
            "",
            "\1\u00c8",
            "\1\u00c9",
            "\1\u00ca",
            "\1\u00cb\11\uffff\1\u00cc",
            "\1\u00cd",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\u00cf\11\uffff\1\u00d0",
            "\1\u00d1",
            "\1\u00d2",
            "\1\u00d3",
            "\1\u00d4",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "",
            "\1\u00d6",
            "\1\u00d7",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\u00d9",
            "\1\u00da",
            "\1\u00db",
            "",
            "\1\u00dc",
            "\1\u00dd",
            "",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\u00df",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\u00e1",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "",
            "\1\u00e3",
            "",
            "\1\u00e4",
            "",
            "\1\u00e5",
            "\1\u00e6",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
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

    class DFA14 extends DFA {

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
            return "1:1: Tokens : ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | RULE_INT_GREATER_ZERO | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA14_0 = input.LA(1);

                        s = -1;
                        if ( (LA14_0=='l') ) {s = 1;}

                        else if ( (LA14_0=='f') ) {s = 2;}

                        else if ( (LA14_0=='a') ) {s = 3;}

                        else if ( (LA14_0=='r') ) {s = 4;}

                        else if ( (LA14_0=='s') ) {s = 5;}

                        else if ( (LA14_0=='c') ) {s = 6;}

                        else if ( (LA14_0=='u') ) {s = 7;}

                        else if ( (LA14_0=='t') ) {s = 8;}

                        else if ( (LA14_0=='n') ) {s = 9;}

                        else if ( (LA14_0=='d') ) {s = 10;}

                        else if ( (LA14_0=='{') ) {s = 11;}

                        else if ( (LA14_0=='}') ) {s = 12;}

                        else if ( (LA14_0==',') ) {s = 13;}

                        else if ( (LA14_0=='e') ) {s = 14;}

                        else if ( ((LA14_0>='1' && LA14_0<='9')) ) {s = 15;}

                        else if ( (LA14_0=='^') ) {s = 16;}

                        else if ( ((LA14_0>='A' && LA14_0<='Z')||LA14_0=='_'||LA14_0=='b'||(LA14_0>='g' && LA14_0<='k')||LA14_0=='m'||(LA14_0>='o' && LA14_0<='q')||(LA14_0>='v' && LA14_0<='z')) ) {s = 17;}

                        else if ( (LA14_0=='0') ) {s = 18;}

                        else if ( (LA14_0=='\"') ) {s = 19;}

                        else if ( (LA14_0=='\'') ) {s = 20;}

                        else if ( (LA14_0=='/') ) {s = 21;}

                        else if ( ((LA14_0>='\t' && LA14_0<='\n')||LA14_0=='\r'||LA14_0==' ') ) {s = 22;}

                        else if ( ((LA14_0>='\u0000' && LA14_0<='\b')||(LA14_0>='\u000B' && LA14_0<='\f')||(LA14_0>='\u000E' && LA14_0<='\u001F')||LA14_0=='!'||(LA14_0>='#' && LA14_0<='&')||(LA14_0>='(' && LA14_0<='+')||(LA14_0>='-' && LA14_0<='.')||(LA14_0>=':' && LA14_0<='@')||(LA14_0>='[' && LA14_0<=']')||LA14_0=='`'||LA14_0=='|'||(LA14_0>='~' && LA14_0<='\uFFFF')) ) {s = 23;}

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA14_19 = input.LA(1);

                        s = -1;
                        if ( ((LA14_19>='\u0000' && LA14_19<='\uFFFF')) ) {s = 50;}

                        else s = 23;

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA14_20 = input.LA(1);

                        s = -1;
                        if ( ((LA14_20>='\u0000' && LA14_20<='\uFFFF')) ) {s = 50;}

                        else s = 23;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 14, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}