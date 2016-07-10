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
	USER_INFO?
; // hostPort uriParameters headers? ;

// skipping telephone_subscriber part from userInfo

USER_INFO
:
	USER
	(
		CH_COLON PASSWORD
	)? CH_ATTHERATE
;

hostPort
:
	host
	(
		CH_COLON PORT
	)?
;

host
:
	hostName
	| IPV4ADDRESS
	| IPV6REFERENCE
;

hostName
:
	(
		DOMAIN_LABEL CH_DOT
	)* TOP_LABEL CH_DOT?
;

uriParameters
:
	(
		CH_SEMICOLON uriParameter
	)*
;

uriParameter
:
	TRANSPORT_PARAM
	| USERPARAM
	| METHOD_PARAM
	| TTL_PARAM
	| maddrParam
	| LR_PARAM
	| OTHER_PARAM
;

maddrParam
:
	TXT_MADDREQUAL host
;

headers
:
	CH_QUESTION HEADER
	(
		CH_QUESTION HEADER
	)*
;

HEADER
:
	H_NAME CH_EQUAL H_VALUE
;

IPV4ADDRESS
:
	DIGIT_1_TO_3 CH_DOT DIGIT_1_TO_3 CH_DOT DIGIT_1_TO_3 CH_DOT DIGIT_1_TO_3
;

IPV6REFERENCE
:
	CH_LEFTBRACKET IPV6ADDRESS CH_RIGHTBRACKET
;

IPV6ADDRESS
:
	HEX_PART
	(
		CH_COLON IPV4ADDRESS
	)?
;

OTHER_PARAM
:
	P_NAME
	(
		CH_EQUAL P_VALUE
	)?
;

P_NAME
:
	PARAM_CHAR+
;

P_VALUE
:
	PARAM_CHAR+
;

METHOD
:
	INVITEM
	| ACKM
	| OPTIONSM
	| BYEM
	| CANCELM
	| REGISTERM
	| EXTENSION_METHOD
;

TRANSPORT_PARAM
:
	TXT_TRANSPORTEQUAL
	(
		TRANSPORT_UDP
		| TRANSPORT_TCP
		| TRANSPORT_SCTP
		| TRANSPORT_TLS
		| OTHER_TRANSPORT
	)
;

USERPARAM
:
	TXT_USEREQUAL
	(
		TXT_PHONE
		| TXT_IP
		| OTHER_USER
	)
;

OTHER_USER
:
	TOKEN
;

METHOD_PARAM
:
	TXT_METHODEQUAL METHOD
;

TTL_PARAM
:
	TXT_TTLEQUAL TTL
;

LR_PARAM
:
	'lr'
;

SIP_SCHEME
:
	'sip:'
;

SIPS_SCHEME
:
	'sips:'
;

TXT_MADDREQUAL
:
	'maddr='
;

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

PORT
:
	DIGIT+
;

fragment
USER
:
	(
		UNRESERVED_ESCAPED
		| USER_UNRESERVED
	)+
;

fragment
PASSWORD
:
	(
		UNRESERVED_ESCAPED
		| CH_AMPERSAND
		| CH_EQUAL
		| CH_PLUS
		| CH_DOLLAR
		| CH_COMMA
	)*
;

fragment
UNRESERVED_ESCAPED
:
	UNRESERVED
	| ESCAPED
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
PARAM_CHAR
:
	PARAM_UNRESERVED
	| UNRESERVED_ESCAPED
;

fragment
ESCAPED
:
	CH_PERCENT HEX_DIG HEX_DIG
;

fragment
UNRESERVED
:
	ALPHA_NUM
	| MARK
;

fragment
HNV_UNRESERVED
:
	CH_RIGHTBRACKET
	| CH_LEFTBRACKET
	| CH_FWDSLASH
	| CH_COLON
	| CH_QUESTION
	| CH_PLUS
	| CH_DOLLAR
;

fragment
H_NAME
:
	(
		HNV_UNRESERVED
		| UNRESERVED_ESCAPED
	)+
;

fragment
H_VALUE
:
	(
		HNV_UNRESERVED
		| UNRESERVED_ESCAPED
	)*
;

fragment
TRANSPORT_UDP
:
	'udp'
;

fragment
TRANSPORT_TCP
:
	'tcp'
;

fragment
TRANSPORT_SCTP
:
	'sctp'
;

fragment
TRANSPORT_TLS
:
	'tls'
;

fragment
OTHER_TRANSPORT
:
	TOKEN
;

fragment
PARAM_UNRESERVED
:
	CH_RIGHTBRACKET
	| CH_LEFTBRACKET
	| CH_FWDSLASH
	| CH_COLON
	| CH_AMPERSAND
	| CH_PLUS
	| CH_DOLLAR
;

fragment
TTL
:
	DIGIT_1_TO_3
;

fragment
TXT_TRANSPORTEQUAL
:
	'transport='
;

fragment
TXT_USEREQUAL
:
	'user='
;

fragment
TXT_METHODEQUAL
:
	'method='
;

fragment
TXT_TTLEQUAL
:
	'ttl='
;

fragment
TXT_PHONE
:
	'phone'
;

fragment
TXT_IP
:
	'ip'
;

fragment
INVITEM
:
	'INVITE'
;

fragment
ACKM
:
	'ACK'
;

fragment
OPTIONSM
:
	'OPTIONSM'
;

fragment
BYEM
:
	'BYE'
;

fragment
CANCELM
:
	'CANCEL'
;

fragment
REGISTERM
:
	'REGISTER'
;

fragment
EXTENSION_METHOD
:
	TOKEN
;

fragment
TOKEN
:
	(
		ALPHA_NUM
		| CH_HYPHEN
		| CH_DOT
		| CH_NOT
		| CH_PERCENT
		| CH_STAR
		| CH_UNDERSCORE
		| CH_PLUS
		| CH_CURLYQUOTE
		| CH_SINGLEQUOTE
		| CH_TILDE
	)+
;

fragment
HEX_SEQ
:
	HEX_DIGIT_1_TO_4
	(
		CH_COLON HEX_DIGIT_1_TO_4
	)*
;

//need to check a better way

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

CH_AMPERSAND
:
	'&'
;

CH_EQUAL
:
	'='
;

CH_PLUS
:
	'+'
;

CH_DOLLAR
:
	'$'
;

CH_COMMA
:
	','
;

CH_SEMICOLON
:
	':'
;

CH_QUESTION
:
	'?'
;

CH_ATTHERATE
:
	'@'
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
