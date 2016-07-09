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

import org.antlr.v4.runtime.tree.TerminalNode;

import antlr4.SipBaseListener;
import antlr4.SipParser;
import antlr4.SipParser.CoreUriContext;

public class SipInfoListener extends SipBaseListener {

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitSipUri(SipParser.SipUriContext ctx) {

        System.out.println(ctx.SIP_SCHEME().getText());
//      
        CoreUriContext core = ctx.coreUri();
        
        System.out.println(core.getText());
        
        TerminalNode node = core.USER_INFO();
        System.out.println(node.getText());
        
        
        
//        
//        
//        System.out.println(coreUriContext.uriParameters() == null);
//        
//        System.out.println(coreUriContext.uriParameters().getText());
//        coreUriContext.uriParameters().uriParameter().forEach(a -> System.out.println(a.getText()));
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitSipsUri(SipParser.SipsUriContext ctx) {

    }

}
