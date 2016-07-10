grammar Sip;

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

sipUri
:
	SIP_SCHEME coreUri
;

sipsUri
:
	SIPS_SCHEME coreUri
;

coreUri
:
	USER_INFO? hostPort
;

hostPort
:
	HOST PORT?
;

USER_INFO
:
	USER CH_ATTHERATE
;

PORT
:
	CH_COLON DIGIT+
;

HOST
:
	HOST_NAME
	| IPV4ADDRESS
	| IPV6REFERENCE
;

fragment
USER
:
	(
		UNRESERVED_ESCAPED
		| USER_UNRESERVED
	)+
;

// Not supporting password currently
// 
//PASSWORD
//:
//	CH_COLON
//	(
//		UNRESERVED_ESCAPED
//		| PASSWORD_UNRESERVED
//	)*
//;

fragment
HOST_NAME
:
	(
		DOMAIN_LABEL CH_DOT
	)* TOP_LABEL CH_DOT?
;

fragment
IPV4ADDRESS
:
	DIGIT_1_TO_3 CH_DOT DIGIT_1_TO_3 CH_DOT DIGIT_1_TO_3 CH_DOT DIGIT_1_TO_3
;

fragment
IPV6REFERENCE
:
	CH_LEFTBRACKET IPV6ADDRESS CH_RIGHTBRACKET
;

fragment
IPV6ADDRESS
:
	HEX_PART
	(
		CH_COLON IPV4ADDRESS
	)?
;

fragment
DOMAIN_LABEL
:
	ALPHA_NUM
	|
	(
		ALPHA_NUM
		(
			ALPHA_NUM
			| CH_HYPHEN
		)* ALPHA_NUM
	)
;

fragment
TOP_LABEL
:
	ALPHA
	|
	(
		ALPHA
		(
			ALPHA_NUM
			| CH_HYPHEN
		)* ALPHA_NUM
	)
;

fragment
HEX_PART
:
	HEX_SEQ
	|
	(
		HEX_SEQ CH_COLON CH_COLON HEX_SEQ?
	)
	|
	(
		CH_COLON CH_COLON HEX_SEQ?
	)
;

fragment
HEX_SEQ
:
	HEX_DIGIT_1_TO_4
	(
		CH_COLON HEX_DIGIT_1_TO_4
	)*
;

fragment
DIGIT_1_TO_3
:
	DIGIT
	| DIGIT DIGIT
	| DIGIT DIGIT DIGIT
;

fragment
HEX_DIGIT_1_TO_4
:
	HEX_DIG
	| HEX_DIG HEX_DIG
	| HEX_DIG HEX_DIG HEX_DIG
	| HEX_DIG HEX_DIG HEX_DIG HEX_DIG
;

SIP_SCHEME
:
	'sip:'
;

SIPS_SCHEME
:
	'sips:'
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

ESCAPED
:
	CH_PERCENT HEX_DIG HEX_DIG
;

fragment
HEX_DIG
:
	DIGIT
	|
	(
		'A' .. 'F'
	)
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

fragment
USER_UNRESERVED
:
	CH_AMPERSAND
	| CH_EQUAL
	| CH_PLUS
	| CH_DOLLAR
	| CH_COMMA
	| CH_SEMICOLON
	| CH_QUESTION
	| CH_FWDSLASH
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

fragment
CH_AMPERSAND
:
	'&'
;

fragment
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

fragment
CH_COMMA
:
	','
;

CH_SEMICOLON
:
	';'
;

fragment
CH_QUESTION
:
	'?'
;

fragment
CH_ATTHERATE
:
	'@'
;

fragment
CH_COLON
:
	':'
;

fragment
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
CH_CURLYQUOTE
:
	'`'
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
