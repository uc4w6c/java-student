package jp.co.example.mvc;

import jp.co.example.annotations.RequestScoped;

import javax.inject.Named;
import java.net.InetAddress;

@Named
@RequestScoped
public class RequestInfo {
    private String path;
    private InetAddress localAddress;
    private String userAgent;
    private String sessionId;

    public RequestInfo(String path, InetAddress localAddress, String userAgent, String sessionId) {
        this.path = path;
        this.localAddress = localAddress;
        this.userAgent = userAgent;
        this.sessionId = sessionId;
    }

    public String getPath() {
        return path;
    }

    public InetAddress getLocalAddress() {
        return localAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getSessionId() {
        return sessionId;
    }
}
