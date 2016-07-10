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

import java.util.LinkedHashMap;
import java.util.Map;

public class SipUriInfo {

    // whether it is sip or sips
    private boolean isSips;

    // userInfo part excluding @
    private String userInfo;

    private String host;

    private int port;

    private Map<String, String> uriParameters = new LinkedHashMap<String, String>(0);

    private Map<String, String> headers = new LinkedHashMap<String, String>(0);

    public String getUserInfo() {

        return userInfo;
    }

    public SipUriInfo setUserInfo(String userInfo) {

        this.userInfo = userInfo;
        return this;
    }

    public String getHost() {

        return host;
    }

    public SipUriInfo setHost(String host) {

        this.host = host;
        return this;
    }

    public int getPort() {

        return port;
    }

    public SipUriInfo setPort(int port) {

        this.port = port;
        return this;
    }

    public Map<String, String> getUriParameters() {

        return uriParameters;
    }

    public SipUriInfo addUriParameters(String paramKey, String paramValue) {

        this.uriParameters.put(paramKey, paramValue);
        return this;
    }

    public Map<String, String> getHeaders() {

        return headers;
    }

    public SipUriInfo addHeaders(String headerKey, String headerValue) {

        this.headers.put(headerKey, headerValue);
        return this;
    }

    public boolean isSips() {

        return isSips;
    }

    public SipUriInfo setSips(boolean isSips) {

        this.isSips = isSips;
        return this;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("SipUriInfo [isSips=");
        builder.append(isSips);
        builder.append(", userInfo=");
        builder.append(userInfo);
        builder.append(", host=");
        builder.append(host);
        builder.append(", port=");
        builder.append(port);
        builder.append(", uriParameters=");
        builder.append(uriParameters);
        builder.append(", headers=");
        builder.append(headers);
        builder.append("]");
        return builder.toString();
    }
}
