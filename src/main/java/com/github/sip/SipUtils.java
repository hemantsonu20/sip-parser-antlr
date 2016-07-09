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
package com.github.sip;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import antlr4.SipLexer;
import antlr4.SipParser;


public class SipUtils {
    
    public static void printSipUri(String header) {
        // Get our lexer
        SipLexer lexer = new SipLexer(new ANTLRInputStream(header));
     
        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);
     
        // Pass the tokens to the parser
        SipParser parser = new SipParser(tokens);
        
        SipInfoListener listener = new SipInfoListener();
        parser.addParseListener(listener);
        parser.addErrorListener(new SipErrorListener());
     
        // Specify our entry point
       parser.sipUri();
    }
    
    public static void main(String[] args) {

        printSipUri("sip:");
    }
}
