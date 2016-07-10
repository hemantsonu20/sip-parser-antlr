grammar UriHeaders;

@header {
/**
 *   Copyright 2016 Pratapi Hemant Patel
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *   
 */
package antlr4;
}

uriHeaders
:
	uriParameters headers? EOF
;

uriParameters
:
	(
		CH_SEMICOLON uriParameter
	)*
;

uriParameter
:
	NAME_OR_VALUE
	(
		CH_EQUAL NAME_OR_VALUE
	)?
;

headers
:
	CH_QUESTION header
	(
		CH_AMPERSAND header
	)*
;

header
:
	NAME_OR_VALUE CH_EQUAL NAME_OR_VALUE
;

fragment
ESCAPED
:
	CH_PERCENT HEX_DIG HEX_DIG
;

fragment
UNRESERVED_ESCAPED
:
	UNRESERVED
	| ESCAPED
;

fragment
UNRESERVED
:
	ALPHA_NUM
	| MARK
;

fragment
HEX_DIG
:
	DIGIT
	| 'A'
	| 'B'
	| 'C'
	| 'D'
	| 'E'
	| 'F'
;

fragment
ALPHA_NUM
:
	ALPHA
	| DIGIT
;

fragment
ALPHA
:
	(
		'a' .. 'z'
		| 'A' .. 'Z'
	)
;

fragment
DIGIT
:
	(
		'0' .. '9'
	)
;

NAME_OR_VALUE
:
	(
		UNRESERVED_ESCAPED
		| PARAM_HNV_UNRESERVED
	)+
;

fragment
PARAM_HNV_UNRESERVED
:
	CH_RIGHTBRACKET
	| CH_LEFTBRACKET
	| CH_FWDSLASH
	| CH_COLON
	| CH_PLUS
	| CH_DOLLAR
;

fragment
MARK
:
	CH_HYPHEN
	| CH_UNDERSCORE
	| CH_DOT
	| CH_NOT
	| CH_TILDE
	| CH_STAR
	| CH_SINGLEQUOTE
	| CH_LEFTBRACE
	| CH_RIGHTBRACE
;

CH_AMPERSAND
:
	'&'
;

CH_EQUAL
:
	'='
;

fragment
CH_PLUS
:
	'+'
;

fragment
CH_DOLLAR
:
	'$'
;

CH_SEMICOLON
:
	';'
;

CH_QUESTION
:
	'?'
;

CH_COLON
:
	':'
;

CH_DOT
:
	'.'
;

fragment
CH_RIGHTBRACKET
:
	']'
;

fragment
CH_LEFTBRACKET
:
	'['
;

fragment
CH_FWDSLASH
:
	'/'
;

fragment
CH_PERCENT
:
	'%'
;

fragment
CH_RIGHTBRACE
:
	')'
;

fragment
CH_LEFTBRACE
:
	'('
;

fragment
CH_HYPHEN
:
	'-'
;

fragment
CH_NOT
:
	'!'
;

fragment
CH_STAR
:
	'*'
;

fragment
CH_UNDERSCORE
:
	'_'
;

fragment
CH_SINGLEQUOTE
:
	'\''
;

fragment
CH_TILDE
:
	'~'
;