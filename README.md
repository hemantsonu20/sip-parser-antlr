# sip-parser-antlr
===========
## Overview
-----------
A sip uri parser written with using [antlr](http://www.antlr.org/)

The grammar of the sip uri is based on the [RFC-3261](https://www.ietf.org/rfc/rfc3261.txt) with some minor exceptions.

A typical sip-uri takes up the form
```
sip:hemant@github.com:80;uriparam1=urivalue1;uriparam2?headerkey1=headerValue1
```

The library parses this sip uri and returns a java class [SipUriInfo](src/main/java/com/github/sip/SipUriInfo.java).
It has following fields.

```java
private boolean isSips;
private String userInfo;
private String host;
private int port = -1;
private Map<String, String> uriParameters;
private Map<String, String> headers;
```

## Examples
-----------
```java
// to parse sip uri
SipUriInfo info = SipUtils.parseSipUri("sip:hemant@github.com:80;uriparam1=urivalue1;uriparam2?headerkey1=headerValue1");

// to parse sips uri
SipUriInfo info = SipUtils.parseSipsUri("sips:hemant@github.com:80;uriparam1=urivalue1;uriparam2?headerkey1=headerValue1");
```
For more examples see [TestSipUtils](src/test/java/com/github/sip/TestSipUtils.java)

## Maven Artifact
-----------
```xml
<dependency>
    <groupId>com.github.hemantsonu20</groupId>
    <artifactId>sip-parser-antlr</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Latest Published Version
-----------
1.0.0 published on July 13th 2016

##License
-----------
[Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)
