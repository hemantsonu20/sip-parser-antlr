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

import java.util.List;
import java.util.Objects;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
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
import antlr4.UriHeadersParser.HeaderContext;
import antlr4.UriHeadersParser.UriHeadersContext;
import antlr4.UriHeadersParser.UriParameterContext;

public class SipUtils {

    /**
     * Method to parse a sip uri to return its details in a more user friendly
     * way. For example a sip uri is of the form
     * 
     * <pre>
     * <sip:hemant@abc.com:80;uriParam1=uriValue1;uriParam2?headerKey=headerValue
     * </pre>
     * 
     * This method will parse the individual details of sip uri such as
     * userInfo("hemant"), host("abc.com"), port(80), map of uri parameters and
     * headers.
     * 
     * The grammar used to parse sip uri as per the
     * https://www.ietf.org/rfc/rfc3261.txt with some minor exception to
     * simplify parsing.
     * 
     * @see SipUriInfo
     * 
     * @param sipUri
     *            string representing a sip uri to be parsed.
     * @return an object of {@link SipUriInfo} class containing sip uri details
     * @throws IllegalStateException
     *             in case an invalid sip uri provided
     */
    public static SipUriInfo parseSipUri(String sipUri) throws IllegalStateException {

        SipUriContext sipUriContext = getSipParser(sipUri).sipUri();
        CoreUriContext coreUriContext = sipUriContext.coreUri();
        SipUriInfo info = new SipUriInfo();
        parseCoreUri(coreUriContext, sipUri, info);
        return info;
    }

    /**
     * Similar to {@link #parseSipUri(String)} with one difference, its a sips
     * uri.
     * 
     * <pre>
     * <sip:hemant@abc.com:80;uriParam1=uriValue1;uriParam2?headerKey=headerValue
     * </pre>
     * 
     * @param sipUri
     *            string representing a sip uri to be parsed.
     * @return an object of {@link SipUriInfo} class containing sip uri details
     * @throws IllegalStateException
     *             in case an invalid sip uri provided
     */
    public static SipUriInfo parseSipsUri(String sipsUri) throws IllegalStateException {

        SipsUriContext sipUriContext = getSipParser(sipsUri).sipsUri();

        CoreUriContext coreUriContext = sipUriContext.coreUri();

        SipUriInfo info = new SipUriInfo().setSips(true);
        parseCoreUri(coreUriContext, sipsUri, info);
        return info;
    }

    private static void parseCoreUri(CoreUriContext coreUriContext, String input, SipUriInfo info) {

        TerminalNode userInfo = coreUriContext.USER_INFO();

        // userInfo is an optional field need to check for null
        if (Objects.nonNull(userInfo)) {

            // removing last char '@'
            StringBuilder builder = new StringBuilder(userInfo.getText());
            builder.deleteCharAt(builder.length() - 1);
            info.setUserInfo(builder.toString());
        }

        HostPortContext hostPortContext = coreUriContext.hostPort();
        info.setHost(hostPortContext.HOST().getText());

        TerminalNode port = hostPortContext.PORT();
        if (Objects.nonNull(port)) {

            // first char is ':', removing it before converting to integer
            info.setPort(Integer.parseInt(port.getText().substring(1)));
        }

        int parsedLength = coreUriContext.getStop().getStopIndex();

        if (parsedLength < input.length() - 1) {

            parseUriHeaders(input.substring(parsedLength + 1), info, parsedLength);
        }
    }

    private static void parseUriHeaders(String input, SipUriInfo info, int parsedLength) {

        UriHeadersContext uriHeadersContext = getUriHeadersParser(input, parsedLength).uriHeaders();

        parserUriParameters(uriHeadersContext.uriParameters().uriParameter(), info);

        // header field is optional may be not present
        if (Objects.nonNull(uriHeadersContext.headers())) {
            parseHeaders(uriHeadersContext.headers().header(), info);
        }
    }

    private static void parserUriParameters(List<UriParameterContext> uriParameterContexts, SipUriInfo info) {

        uriParameterContexts.forEach(e -> {

            // this list has max element count two, one key and other value
            // min is one because key is mandatory, value is optional
                List<TerminalNode> paramKeyValues = e.NAME_OR_VALUE();

                String paramKey = paramKeyValues.get(0).getText();
                String paramValue = null;

                // in case value present
                if (paramKeyValues.size() == 2) {
                    paramValue = paramKeyValues.get(1).getText();
                }
                info.addUriParameters(paramKey, paramValue);
            });
    }

    private static void parseHeaders(List<HeaderContext> headerContexts, SipUriInfo info) {

        headerContexts.forEach(e -> {

            // this list has element count two, one key and other value
                List<TerminalNode> headerKeyValues = e.NAME_OR_VALUE();
                info.addHeaders(headerKeyValues.get(0).getText(), headerKeyValues.get(1).getText());
            });
    }

    private static SipParser getSipParser(String input) {

        SipErrorListener listener = new SipErrorListener();
        
        // Get our lexer
        SipLexer lexer = new SipLexer(new ANTLRInputStream(input));
        lexer.addErrorListener(listener);
        
        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Pass the tokens to the parser
        SipParser parser = new SipParser(tokens);
        parser.setErrorHandler(new BailErrorStrategy());
        parser.addErrorListener(listener);
        return parser;
    }

    private static UriHeadersParser getUriHeadersParser(String input, int parsedLength) {

        SipErrorListener listener = new SipErrorListener(parsedLength);
        
        // Get our lexer
        UriHeadersLexer lexer = new UriHeadersLexer(new ANTLRInputStream(input));
        lexer.addErrorListener(listener);

        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Pass the tokens to the parser
        UriHeadersParser parser = new UriHeadersParser(tokens);
        parser.addErrorListener(listener);
        return parser;
    }
}
