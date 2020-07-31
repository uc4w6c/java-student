package jp.co.example.sample.controller;

import jp.co.example.mvc.RequestInfo;
import jp.co.example.annotations.Path;

import javax.inject.Inject;
import javax.inject.Named;

/*
@Named
@Path("info")
public class RequestInfoControllerBK {
    @Inject
    RequestInfo requestInfo;

    @Path("index")
    public String index() {
        return String.format("<h1>Info</h1>"
                        + "Host:%s<br/>"
                        + "Path:%s<br/>"
                        + "UserAgent:%s<br/>"
                        + "SessionId:%s<br/>",
                requestInfo.getLocalAddress(),
                requestInfo.getPath(),
                requestInfo.getUserAgent(),
                requestInfo.getSessionId());

    }
}
 */
