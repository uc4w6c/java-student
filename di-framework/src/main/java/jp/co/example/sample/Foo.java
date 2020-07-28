package jp.co.example.sample;

import jp.co.example.annotations.InvokeLog;

import javax.inject.Named;

@Named
public class Foo {

    String getMessage() {
        return "Hello!";
    }

    @InvokeLog
    String getName() {
        return "foo!";
    }
}
