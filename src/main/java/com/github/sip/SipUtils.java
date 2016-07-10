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
import antlr4.UriHeadersLexer;
import antlr4.UriHeadersParser;
import antlr4.UriHeadersParser.UriHeadersContext;

public class SipUtils {

    public static SipUriInfo parseSipUri(String sipUri) throws IllegalStateException {

        SipUriContext sipUriContext = getSipParser(sipUri).sipUri();

        log("exce" + sipUriContext.exception);
        
        log("sip: " + sipUriContext.SIP_SCHEME());
        
        log("index" + sipUriContext.getStop().getStopIndex());
        

        
        log("toString" + sipUriContext.toString());
        
        
        
        CoreUriContext coreUriContext = sipUriContext.coreUri();
        

        SipUriInfo info = new SipUriInfo();
        
        parseCoreUri(coreUriContext, sipUri, info);
        
        return info;
    }

    public static SipUriInfo parseSipsUri(String sipsUri) throws IllegalStateException {

        SipsUriContext sipUriContext = getSipParser(sipsUri).sipsUri();

        CoreUriContext coreUriContext = sipUriContext.coreUri();

        SipUriInfo info = new SipUriInfo().setSips(true);
        parseCoreUri(coreUriContext, sipsUri, info);
        return info;
    }

    private static void parseCoreUri(CoreUriContext coreUriContext, String input, SipUriInfo info) {

        log("uriIndex: " + coreUriContext.getStop().getStopIndex());
        
        TerminalNode userInfo = coreUriContext.USER_INFO();

        // userInfo is an optional field need to check for null
        if (Objects.nonNull(userInfo)) {
            log("userinfo:" + userInfo.getText());
            
            // removing last char '@'
            StringBuilder builder = new StringBuilder(userInfo.getText());
            builder.deleteCharAt(builder.length() - 1);
            info.setUserInfo(builder.toString());
        }

        HostPortContext hostPortContext = coreUriContext.hostPort();

        log(hostPortContext.HOST().getText());
        
        info.setHost(hostPortContext.HOST().getText());
        

        TerminalNode port = hostPortContext.PORT();
        if(Objects.nonNull(port)) {
            
            log("port: " + port.getText());
            // first char is ':', removing it before converting to integer
            info.setPort(Integer.parseInt(port.getText().substring(1)));
        }
        
        int parsedLength = coreUriContext.getStop().getStopIndex() + 1;
        
        
        if(parsedLength < input.length()) {
        
            parseUriHeaders(input.substring(parsedLength), info);
        }
        // TODO pass startIndex in error listener
        
    }

    
    private static void parseUriHeaders(String input, SipUriInfo info) {

        UriHeadersContext uriHeadersContext = getUriHeadersParser(input).uriHeaders();
        
        log("exce" + uriHeadersContext.exception);
        

        
        log("toString" + uriHeadersContext.toString());
        
        // TODO
        
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
    
    private static UriHeadersParser getUriHeadersParser(String input) {

        log("uriPart: " + input);
        
        // Get our lexer
        UriHeadersLexer lexer = new UriHeadersLexer(new ANTLRInputStream(input));

        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Pass the tokens to the parser
        UriHeadersParser parser = new UriHeadersParser(tokens);

        parser.addErrorListener(new SipErrorListener());

        return parser;
    }

    private static void log(String text) {

        System.out.println("]" + text + "[");

    }

    public static void main(String[] args) {

        SipUriInfo info = parseSipUri("sip:hemant@abc.com:8:0;sdfssdfsfsf");
        log(info.toString());
    }
}
