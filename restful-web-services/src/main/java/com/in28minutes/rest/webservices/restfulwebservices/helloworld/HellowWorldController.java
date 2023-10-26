package com.in28minutes.rest.webservices.restfulwebservices.helloworld;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellowWorldController {

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
}
