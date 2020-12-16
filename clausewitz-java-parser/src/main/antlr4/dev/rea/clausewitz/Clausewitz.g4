grammar Clausewitz;
//author (Realiant Daniel Fedotov)

//Derived from JSON grammar, provided by Terrence Parr in his Definitive ANTLR 4 Reference, then changed and changed....
//Parser for clausewitz language.
//This file is unlicensed, use it as you wish. If you want to give credit, keep the author line.

file : (pair)* ;

clause : '{' (pair)* '}' ;

pair : STRING VALUE_OPERATOR value ;

VALUE_OPERATOR : '=' | '<>' | '>' | '<' | '<=' | '>=' ;

array : '{' value+ '}' ;

value :
	 INT
	|PERCENT
	|FLOAT
	|DATE
	|STRING
	|clause
	|array ;

INT : '-'? [0-9]+ ;

PERCENT : '-'? [0-9]+ '%' ;

FLOAT : '-'? [0-9]+ '.' [0-9]+ ;

DATE : [0-9]+ '.' [0-9]+ '.' [0-9]+ ('.' [0-9]+)*;

STRING : '"' (~["])* '"'
		| [A-Za-z][A-Za-z_0-9.%-]* ;

WS : [ \t\n\r] + -> skip ;
COMMENT : '#'~[\r\n]* -> channel(HIDDEN) ;
