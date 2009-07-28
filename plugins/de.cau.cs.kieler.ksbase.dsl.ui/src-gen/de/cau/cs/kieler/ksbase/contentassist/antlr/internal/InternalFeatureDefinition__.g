lexer grammar InternalFeatureDefinition;
@header {
package de.cau.cs.kieler.ksbase.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.common.editor.contentassist.antlr.internal.Lexer;
}

T11 : 'ModelPackage = ' ;
T12 : ';' ;
T13 : 'FeatureMenuTitle = ' ;
T14 : 'ModelPackagePath = ' ;
T15 : 'DiagramPackage = ' ;
T16 : 'TransformationFile = ' ;
T17 : 'Feature' ;
T18 : '{' ;
T19 : 'MethodName = ' ;
T20 : 'MenuEntry = ' ;
T21 : '}' ;
T22 : 'File = ' ;
T23 : 'Parameter = ' ;

// $ANTLR src "../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g" 1059
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g" 1061
RULE_INT : ('0'..'9')+;

// $ANTLR src "../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g" 1063
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g" 1065
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g" 1067
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g" 1069
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g" 1071
RULE_ANY_OTHER : .;


