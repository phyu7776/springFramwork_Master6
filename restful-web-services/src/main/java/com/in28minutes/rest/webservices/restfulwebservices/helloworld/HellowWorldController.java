package com.in28minutes.rest.webservices.restfulwebservices.helloworld;


import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HellowWorldController {

    private MessageSource messageSource;

    public HellowWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    //@RequestMapping 에서는 Method를 입력해야 하지만 GetMapping, PostMapping  ... method 를 입력할 필요가없은 편리 ~~
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "HelloWorld";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("HelloWorld");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean("HelloWorld" + name);
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
    }
}
