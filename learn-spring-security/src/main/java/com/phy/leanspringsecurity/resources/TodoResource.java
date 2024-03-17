package com.phy.leanspringsecurity.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoResource {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static List<Todo> TODOS_LIST() {
        return List.of(new Todo("test", "Learn AWS"),
                new Todo("test", "GET AWS Certified"));
    }

    @GetMapping("/todos")
    public List<Todo> retrieveAllTodos() {
        return TODOS_LIST();
    }

    @GetMapping("/users/{username}/todos")
    public Todo retrieveTodosForASpecificUser(@PathVariable String username) {
        return TODOS_LIST().get(0);
    }

    @PostMapping("/users/{username}/todos")
    public void cresteTodoTodosForASpecificUser(@PathVariable String username, @RequestBody Todo todo) {
        logger.info("create {} for {}", todo, username);
    }


}

record Todo (String username, String description) {

}
