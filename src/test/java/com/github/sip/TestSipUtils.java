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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.Map;

import org.testng.annotations.Test;

public class TestSipUtils {

    @Test
    public void testSipUriWithHostPort() {

        SipUriInfo info = SipUtils.parseSipUri("sip:abc.com");
        assertFalse(info.isSips());
        assertEquals("abc.com", info.getHost());

        info = SipUtils.parseSipUri("sip:hemant@abc.com");
        assertFalse(info.isSips());
        assertEquals("hemant", info.getUserInfo());
        assertEquals("abc.com", info.getHost());

        info = SipUtils.parseSipUri("sip:hemant@abc.com:80");
        assertFalse(info.isSips());
        assertEquals("hemant", info.getUserInfo());
        assertEquals("abc.com", info.getHost());
        assertEquals(80, info.getPort());

        info = SipUtils.parseSipUri("sip:hemant@abc.com:80");
        assertFalse(info.isSips());
        assertEquals("hemant", info.getUserInfo());
        assertEquals("abc.com", info.getHost());
        assertEquals(80, info.getPort());
    }

    @Test
    public void testSipsUriWithHostPort() {

        SipUriInfo info = SipUtils.parseSipsUri("sips:abc.com");
        assertTrue(info.isSips());
        assertEquals("abc.com", info.getHost());

        info = SipUtils.parseSipsUri("sips:hemant@abc.com");
        assertTrue(info.isSips());
        assertEquals("hemant", info.getUserInfo());
        assertEquals("abc.com", info.getHost());

        info = SipUtils.parseSipsUri("sips:hemant@abc.com:80");
        assertTrue(info.isSips());
        assertEquals("hemant", info.getUserInfo());
        assertEquals("abc.com", info.getHost());
        assertEquals(80, info.getPort());

        info = SipUtils.parseSipsUri("sips:hemant@abc.com:80");
        assertTrue(info.isSips());
        assertEquals("hemant", info.getUserInfo());
        assertEquals("abc.com", info.getHost());
        assertEquals(80, info.getPort());
    }

    @Test
    public void testSipUriWithUriParameters() {

        SipUriInfo info = SipUtils.parseSipUri("sip:abc.com;param1");
        assertFalse(info.isSips());
        assertEquals("abc.com", info.getHost());
        Map<String, String> params = info.getUriParameters();
        assertTrue(params.containsKey("param1"));
        assertEquals(null, params.get("param1"));

        info = SipUtils.parseSipUri("sip:hemant@abc.com;param1=value1");
        assertFalse(info.isSips());
        assertEquals("hemant", info.getUserInfo());
        assertEquals("abc.com", info.getHost());
        params = info.getUriParameters();
        assertEquals("value1", params.get("param1"));

        info = SipUtils.parseSipUri("sip:hemant@abc.com:80;param1=value1;param2");
        assertFalse(info.isSips());
        assertEquals("hemant", info.getUserInfo());
        assertEquals("abc.com", info.getHost());
        assertEquals(80, info.getPort());
        params = info.getUriParameters();
        assertEquals("value1", params.get("param1"));
        assertTrue(params.containsKey("param2"));
        assertEquals(null, params.get("param2"));

        info = SipUtils.parseSipUri("sip:hemant@abc.com:80;param1=value1;param2;param3=value3");
        assertFalse(info.isSips());
        assertEquals("hemant", info.getUserInfo());
        assertEquals("abc.com", info.getHost());
        assertEquals(80, info.getPort());
        params = info.getUriParameters();
        assertEquals("value1", params.get("param1"));
        assertTrue(params.containsKey("param2"));
        assertEquals(null, params.get("param2"));
        assertEquals("value3", params.get("param3"));
    }

    @Test
    public void testSipUriWithHeaders() {

        SipUriInfo info = SipUtils.parseSipUri("sip:abc.com;param1?h1=v1");
        assertFalse(info.isSips());
        assertEquals("abc.com", info.getHost());
        Map<String, String> params = info.getUriParameters();
        assertTrue(params.containsKey("param1"));
        assertEquals(null, params.get("param1"));
        Map<String, String> headers = info.getHeaders();
        assertEquals("v1", headers.get("h1"));

        info = SipUtils.parseSipUri("sip:hemant@abc.com;param1=value1?h1=v1&h2=v2");
        assertFalse(info.isSips());
        assertEquals("hemant", info.getUserInfo());
        assertEquals("abc.com", info.getHost());
        params = info.getUriParameters();
        assertEquals("value1", params.get("param1"));
        headers = info.getHeaders();
        assertEquals("v1", headers.get("h1"));
        assertEquals("v2", headers.get("h2"));

        info = SipUtils.parseSipUri("sip:hemant@abc.com:80;param1=value1;param2?h1=v1&h2=v2&h3=v3");
        assertFalse(info.isSips());
        assertEquals("hemant", info.getUserInfo());
        assertEquals("abc.com", info.getHost());
        assertEquals(80, info.getPort());
        params = info.getUriParameters();
        assertEquals("value1", params.get("param1"));
        assertTrue(params.containsKey("param2"));
        assertEquals(null, params.get("param2"));
        headers = info.getHeaders();
        assertEquals("v1", headers.get("h1"));
        assertEquals("v2", headers.get("h2"));
        assertEquals("v3", headers.get("h3"));

        info = SipUtils.parseSipUri("sip:hemant@abc.com:80;param1=value1;param2;param3=value3?h1=v1&h2=v2&h3=v3&h4=v4");
        assertFalse(info.isSips());
        assertEquals("hemant", info.getUserInfo());
        assertEquals("abc.com", info.getHost());
        assertEquals(80, info.getPort());
        params = info.getUriParameters();
        assertEquals("value1", params.get("param1"));
        assertTrue(params.containsKey("param2"));
        assertEquals(null, params.get("param2"));
        assertEquals("value3", params.get("param3"));
        headers = info.getHeaders();
        assertEquals("v1", headers.get("h1"));
        assertEquals("v2", headers.get("h2"));
        assertEquals("v3", headers.get("h3"));
        assertEquals("v4", headers.get("h4"));
    }
    
    @Test
    public void testSipUriWithComplexInputs() {

        SipUriInfo info = SipUtils.parseSipUri("sip:198.11.254.102:5060;transport=udp;lr");
        assertFalse(info.isSips());
        assertEquals("198.11.254.102", info.getHost());
        assertEquals(5060, info.getPort());
        Map<String, String> params = info.getUriParameters();
        assertEquals("udp", params.get("transport"));
        assertTrue(params.containsKey("lr"));
        assertEquals(null, params.get("lr"));

        info = SipUtils.parseSipUri("sip:+919013982184@67.231.5.176:5060");
        assertFalse(info.isSips());
        assertEquals("+919013982184", info.getUserInfo());
        assertEquals("67.231.5.176", info.getHost());
        assertEquals(5060, info.getPort());

        info = SipUtils.parseSipUri("sip:919013982184@sip-trunk-bandwidth.tropo.com");
        assertFalse(info.isSips());
        assertEquals("919013982184", info.getUserInfo());
        assertEquals("sip-trunk-bandwidth.tropo.com", info.getHost());

        info = SipUtils.parseSipUri("sip:919013982184@github-ssl-dev.tropo.com:5061;x-php-called=+12146420000;user=phone;x-php-tenant=dec55d7a-9d08-4a12-a9a7-052939c29ae0");
        assertFalse(info.isSips());
        assertEquals("919013982184", info.getUserInfo());
        assertEquals("github-ssl-dev.tropo.com", info.getHost());
        assertEquals(5061, info.getPort());
        params = info.getUriParameters();
        assertEquals("+12146420000", params.get("x-php-called"));
        assertEquals("phone", params.get("user"));
        assertEquals("dec55d7a-9d08-4a12-a9a7-052939c29ae0", params.get("x-php-tenant"));
    }

    @Test
    public void testInvalidSipUri() {

        try {
            // sip: missing
            SipUtils.parseSipUri("abc.com");
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
        }

        try {
            // host missing
            SipUtils.parseSipUri("sip:hemant@");
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
        }

        try {
            // port is not number
            SipUtils.parseSipUri("sip:abc.com:eighty");
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
        }

        try {
            // host missing
            SipUtils.parseSipUri("sip:abc.com:eighty");
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
        }

        try {
            // invalid ip
            SipUtils.parseSipUri("sip:hemant@192.168.0.555555:80");
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
        }

        try {
            // space shouldn't be there
            SipUtils.parseSipUri("sip: hemant@abc.com");
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
        }

        try {
            // semi colon should only be there if there are uri parameters
            SipUtils.parseSipUri("sip:hemant@abc.com;");
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
        }

        try {
            // uri parameters with empty value not permitted
            SipUtils.parseSipUri("sip:hemant@abc.com;u1=");
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
        }

        try {
            // headers without value not permitted
            SipUtils.parseSipUri("sip:hemant@abc.com;param1?h1");
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
        }

        try {
            // headers with empty value not permitted
            SipUtils.parseSipUri("sip:hemant@abc.com;param1?h1=");
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
        }
    }    
}
