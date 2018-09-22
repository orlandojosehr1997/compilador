package scannercompi;
import static scannercompi.Token.*;
%%
%class Lexer
%type Token
%line
%ignorecase
L = [a-zA-Z\_]
D = [0-9]
WHITE=[ \t\r]
ENDLINE=[\n]
Exponent = [eE] [\+\-]? 
Float = [\+\-]? {D}+ \. {D}+ 
CChar = [^\'\\\n\r] | {EscChar}
SChar = [^\"\\\n\r] | {EscChar}
EscChar = \\[ntbrf\\\'\"] | {OctalEscape}
OctalEscape = \\[0-7] | \\[0-7][0-7] | \\[0-3][0-7][0-7]
ComentarioLinea = "//".*
ComentarioBloque = "\(\*" ({WHITE}|{ENDLINE}|{L}|{D}|{SChar}|[\"])* "\*\)" | "\{" ({WHITE}|{ENDLINE}|{L}|{D}|{SChar}|[\"])* "\}"
CaracterInvalidoIdentificador = [ \,\<\>\`\~\!\&\#\|\.\/\@\$\%\^\*\=\+]
%{
public String lexeme;
%}
%%

{ComentarioBloque} {/*Ignore*/}
{ComentarioLinea} {/*Ignore*/}
{WHITE} {/*Ignore*/}
{ENDLINE} {/*Ignore*/}

\'{CChar}\' | \# {D}+ {lexeme=yytext(); return LITERAL_CHAR;}
\"({WHITE}|{ENDLINE}|{SChar})*\" {lexeme=yytext(); return LITERAL_STRING;}
{Float} | {Float} {Exponent} {D}+  {lexeme=yytext(); return LITERAL_FLOTANTE;}
[\+\-]?("(-"{D}+")")|[\+\-]?{D}+ {lexeme=yytext(); return LITERAL_ENTERO;}

"XOR" {lexeme = "XOR"; return OPERADOR;}
"WRITE" {lexeme = "WRITE"; return PALABRAS_RESERVADAS;}
"WITH" {lexeme = "WITH"; return PALABRAS_RESERVADAS;}
"WHILE" {lexeme = "WHILE"; return PALABRAS_RESERVADAS;}
"VAR" {lexeme = "VAR"; return PALABRAS_RESERVADAS;}
"UNTIL" {lexeme = "UNTIL"; return PALABRAS_RESERVADAS;}
"TYPE" {lexeme = "TYPE"; return PALABRAS_RESERVADAS;}
"TRUE" {lexeme = "TRUE"; return PALABRAS_RESERVADAS;}
"TO" {lexeme = "TO"; return PALABRAS_RESERVADAS;}
"THEN" {lexeme = "THEN"; return PALABRAS_RESERVADAS;}
"STRING" {lexeme = "STRING"; return PALABRAS_RESERVADAS;}
"SHORTINT" {lexeme = "SHORTINT"; return PALABRAS_RESERVADAS;}
"SET" {lexeme = "SET"; return PALABRAS_RESERVADAS;}
"REPEAT" {lexeme = "REPEAT"; return PALABRAS_RESERVADAS;}
"RECORD" {lexeme = "RECORD"; return PALABRAS_RESERVADAS;}
"REAL" {lexeme = "REAL"; return PALABRAS_RESERVADAS;}
"READ" {lexeme = "READ"; return PALABRAS_RESERVADAS;}
"PROGRAM" {lexeme = "PROGRAM"; return PALABRAS_RESERVADAS;}
"PROCEDURE" {lexeme = "PROCEDURE"; return PALABRAS_RESERVADAS;}
"PACKED" {lexeme = "PACKED"; return PALABRAS_RESERVADAS;}
"OR" {lexeme = "OR"; return OPERADOR;}
"OF" {lexeme = "OF"; return PALABRAS_RESERVADAS;}
"NOT" {lexeme = "NOT"; return OPERADOR;}
"NIL" {lexeme = "NIL"; return PALABRAS_RESERVADAS;}
"MOD" {lexeme = "MOD"; return OPERADOR;}
"LONGINT" {lexeme = "LONGINT"; return PALABRAS_RESERVADAS;}
"LABEL" {lexeme = "LABEL"; return PALABRAS_RESERVADAS;}
"INT" {lexeme = "INT"; return PALABRAS_RESERVADAS;}
"INLINE" {lexeme = "INLINE"; return PALABRAS_RESERVADAS;}
"IN" {lexeme = "IN"; return PALABRAS_RESERVADAS;}
"IF" {lexeme = "IF"; return PALABRAS_RESERVADAS;}
"GOTO" {lexeme = "GOTO"; return PALABRAS_RESERVADAS;}
"FUNCTION" {lexeme = "FUNCTION"; return PALABRAS_RESERVADAS;}
"FORWARD" {lexeme = "FORWARD"; return PALABRAS_RESERVADAS;}
"FOR" {lexeme = "FOR"; return PALABRAS_RESERVADAS;}
"FILE" {lexeme = "FILE"; return PALABRAS_RESERVADAS;}
"FALSE" {lexeme = "FALSE"; return PALABRAS_RESERVADAS;}
"END" {lexeme = "END"; return PALABRAS_RESERVADAS;}
"ELSE" {lexeme = "ELSE"; return PALABRAS_RESERVADAS;}
"DOWNTO" {lexeme = "DOWNTO"; return PALABRAS_RESERVADAS;}
"DO" {lexeme = "DO"; return PALABRAS_RESERVADAS;}
"DIV" {lexeme = "DIV"; return OPERADOR;}
"CONST" {lexeme = "CONST"; return PALABRAS_RESERVADAS;}
"CHAR" {lexeme = "CHAR"; return PALABRAS_RESERVADAS;}
"CASE" {lexeme = "CASE"; return PALABRAS_RESERVADAS;}
"BYTE" {lexeme = "BYTE"; return PALABRAS_RESERVADAS;}
"BOOLEAN" {lexeme = "BOOLEAN"; return PALABRAS_RESERVADAS;}
"BEGIN" {lexeme = "BEGIN"; return PALABRAS_RESERVADAS;}
"ARRAY" {lexeme = "ARRAY"; return PALABRAS_RESERVADAS;}
"AND" {lexeme = "AND"; return OPERADOR;}

"]" {lexeme = "]"; return OPERADOR;}
"[" {lexeme = "["; return OPERADOR;}
">>=" {lexeme = ">>="; return OPERADOR;}
">>" {lexeme = ">>"; return OPERADOR;}
">=" {lexeme = ">="; return OPERADOR;}
">" {lexeme = ">"; return OPERADOR;}
"=" {lexeme = "="; return OPERADOR;}
"<>" {lexeme = "<>"; return OPERADOR;}
"<=" {lexeme = "<="; return OPERADOR;}
"<<=" {lexeme = "<<="; return OPERADOR;}
"<<" {lexeme = "<<"; return OPERADOR;}
"<" {lexeme = "<"; return OPERADOR;}
";" {lexeme = ";"; return OPERADOR;}
":=" {lexeme = ":="; return OPERADOR;}
":" {lexeme = ":"; return OPERADOR;}
"/=" {lexeme = "/="; return OPERADOR;}
"/" {lexeme = "/"; return OPERADOR;}
"." {lexeme = "."; return OPERADOR;}
"-=" {lexeme = "-="; return OPERADOR;}
"--" {lexeme = "--"; return OPERADOR;}
"-" {lexeme = "-"; return OPERADOR;}
"," {lexeme = ","; return OPERADOR;}
"+=" {lexeme = "+="; return OPERADOR;}
"++" {lexeme = "++"; return OPERADOR;}
"+" {lexeme = "+"; return OPERADOR;}
"*=" {lexeme = "*="; return OPERADOR;}
"*" {lexeme = "*"; return OPERADOR;}
")" {lexeme = ")"; return OPERADOR;}
"(" {lexeme = "("; return OPERADOR;}

{L}({L}|{D})* {lexeme=yytext(); return IDENTIFICADOR;}

{D}+"\." {lexeme=yytext(); return ERROR_FLOAT;}
"\."{D}+ {lexeme=yytext(); return ERROR_FLOAT;}
[\+\-]?{D}+ ["\."]? ({D}*{L}+{D}*)+ | [\+\-]?({D}+{L}+{D}*)+ ["\."]? {D}* | [\+\-]?({D}+{L}+{D}*)+ ["\."]? ({D}*{L}+{D}*)+ {lexeme=yytext(); return ERROR_INT;}
. {lexeme=yytext(); return ERROR;}