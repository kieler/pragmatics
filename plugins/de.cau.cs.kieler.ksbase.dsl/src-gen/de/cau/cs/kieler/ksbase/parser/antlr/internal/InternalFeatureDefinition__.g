lexer grammar InternalFeatureDefinition;
@header {
package de.cau.cs.kieler.ksbase.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T11 : 'ModelPackage = ' ;
T12 : ';' ;
T13 : 'ModelPackagePath = ' ;
T14 : 'FeatureMenuTitle = ' ;
T15 : 'TransformationFile = ' ;
T16 : 'Feature' ;
T17 : '{' ;
T18 : 'File = ' ;
T19 : 'MethodName = ' ;
T20 : 'NumParameter = ' ;
T21 : 'Parameter = ' ;
T22 : 'MenuEntry = ' ;
T23 : '}' ;

// $ANTLR src "../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g" 418
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g" 420
RULE_INT : ('0'..'9')+;

// $ANTLR src "../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g" 422
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g" 424
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g" 426
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g" 428
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g" 430
RULE_ANY_OTHER : .;


