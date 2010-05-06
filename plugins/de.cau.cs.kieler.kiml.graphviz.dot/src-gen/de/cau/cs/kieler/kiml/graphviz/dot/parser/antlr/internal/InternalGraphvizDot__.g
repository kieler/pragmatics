lexer grammar InternalGraphvizDot;
@header {
package de.cau.cs.kieler.kiml.graphviz.dot.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T13 : 'strict' ;
T14 : '{' ;
T15 : '}' ;
T16 : ';' ;
T17 : '[' ;
T18 : ',' ;
T19 : ']' ;
T20 : 'subgraph' ;
T21 : '=' ;
T22 : ':' ;
T23 : '->' ;
T24 : '--' ;
T25 : 'graph' ;
T26 : 'digraph' ;
T27 : 'node' ;
T28 : 'edge' ;
T29 : 'n' ;
T30 : 'ne' ;
T31 : 'e' ;
T32 : 'se' ;
T33 : 's' ;
T34 : 'sw' ;
T35 : 'w' ;
T36 : 'nw' ;
T37 : 'c' ;
T38 : '_' ;

// $ANTLR src "../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g" 1376
RULE_INT : '-'? ('0'..'9')+;

// $ANTLR src "../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g" 1378
RULE_ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g" 1380
RULE_FLOAT : '-'? ('0'..'9')* '.' ('0'..'9')+;

// $ANTLR src "../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g" 1382
RULE_STRING : '"' ('\\' '"'|~('"'))* '"';

// $ANTLR src "../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g" 1384
RULE_PREC_LINE : '#' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g" 1386
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g" 1388
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g" 1390
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g" 1392
RULE_ANY_OTHER : .;


