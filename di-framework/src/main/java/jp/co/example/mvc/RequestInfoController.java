package jp.co.example.mvc;

import jp.co.example.annotations.Path;

import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author naoki
 */
@Named
@Path("info")
public class RequestInfoController {
    @Inject
    RequestInfo requestInfo;

    @Path("index")
    public String index() {
        return String.format("<h1>Info</h1>Host:%s<br/>Path:%s<br/>UserAgent:%s<br/>",
                requestInfo.getLocalAddress(), requestInfo.getPath(), requestInfo.getUserAgent());

    }
}
