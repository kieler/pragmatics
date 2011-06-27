lexer grammar InternalKaom;
@header {
package de.cau.cs.kieler.kaom.text.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T14 : 'entity' ;
T15 : '{' ;
T16 : '}' ;
T17 : ';' ;
T18 : 'link' ;
T19 : 'to' ;
T20 : 'port' ;
T21 : 'relation' ;
T22 : '@' ;
T23 : '(' ;
T24 : ')' ;
T25 : '[' ;
T26 : ']' ;
T27 : 'import' ;
T28 : '.' ;

// $ANTLR src "../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g" 1851
RULE_COMMENT_ANNOTATION : '/**' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g" 1853
RULE_ML_COMMENT : '/*' ~('*') ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g" 1855
RULE_INT : '-'? ('0'..'9')+;

// $ANTLR src "../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g" 1857
RULE_FLOAT : ('-'? ('0'..'9')+ '.' ('0'..'9')* (('e'|'E') ('+'|'-')? ('0'..'9')+)? 'f'?|'-'? ('0'..'9')+ 'f');

// $ANTLR src "../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g" 1859
RULE_BOOLEAN : ('true'|'false');

// $ANTLR src "../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g" 1861
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g" 1863
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g" 1865
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g" 1867
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g" 1869
RULE_ANY_OTHER : .;


