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

import java.util.Objects;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.TerminalNode;

import antlr4.SipLexer;
import antlr4.SipParser;
import antlr4.SipParser.CoreUriContext;
import antlr4.SipParser.HostPortContext;
import antlr4.SipParser.SipUriContext;
import antlr4.SipParser.SipsUriContext;

public class SipUtils {

    public static SipUriInfo parseSipUri(String sipUri) {

        SipUriContext sipUriContext = getSipParser(sipUri).sipUri();

        CoreUriContext coreUriContext = sipUriContext.coreUri();

        return parseCoreUri(coreUriContext);
    }

    public static SipUriInfo parseSipsUri(String sipsUri) {

        SipsUriContext sipUriContext = getSipParser(sipsUri).sipsUri();

        CoreUriContext coreUriContext = sipUriContext.coreUri();

        return parseCoreUri(coreUriContext);
    }

    private static SipUriInfo parseCoreUri(CoreUriContext coreUriContext) {

        TerminalNode userInfo = coreUriContext.USER_INFO();

        // userInfo is an optional field need to check for null
        if (Objects.nonNull(userInfo)) {
            log(userInfo.getText());
        }

        HostPortContext hostPortContext = coreUriContext.hostPort();

        log(hostPortContext.getText());

        log(hostPortContext.HOST().getText());

        TerminalNode port = hostPortContext.PORT();
        if(Objects.nonNull(port)) {
            
            log(port.getText());
        }
        

        return null;
    }

    private static SipParser getSipParser(String input) {

        // Get our lexer
        SipLexer lexer = new SipLexer(new ANTLRInputStream(input));

        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Pass the tokens to the parser
        SipParser parser = new SipParser(tokens);

        // SipInfoListener listener = new SipInfoListener();
        // parser.addParseListener(listener);
        parser.addErrorListener(new SipErrorListener());

        return parser;
    }

    private static void log(String text) {

        System.out.println("]" + text + "[");

    }

    public static void main(String[] args) {

        parseSipUri("sip:hemant@abc.com:80;sdfssdfsfsf");
    }
}
