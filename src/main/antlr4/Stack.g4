grammar Stack;

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

    sipUri          : SIP_SCHEME coreUri EOF ;
    coreUri         : USER_INFO? hostPort ;
    hostPort        : 'abc.com' ;

    SIP_SCHEME           : 'sip:';
    USER_INFO            : USER PASSWORD? '@' ;
    fragment USER        : ALPHA_NUM+ ;
    fragment PASSWORD    : ':' ALPHA_NUM+ ;
    fragment ALPHA_NUM   :  ALPHA | DIGIT ;
    fragment ALPHA       : ('a'..'z' | 'A'..'Z') ;
    fragment DIGIT       : ('0'..'9') ;