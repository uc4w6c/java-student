package jp.co.example.sample.controller;

import jp.co.example.annotations.Path;

import javax.inject.Named;
import java.time.LocalDateTime;

@Named
@Path("")
public class IndexController {
    @Path("")
    public String index() {
        return "<h1>Hello</h1>" + LocalDateTime.now();
    }

    @Path("message")
    public String mes() {
        return "<h1>Message</h1>Nice to meet you!";
    }
}
