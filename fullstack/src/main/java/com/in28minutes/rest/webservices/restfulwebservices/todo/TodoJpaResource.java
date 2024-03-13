package com.in28minutes.rest.webservices.restfulwebservices.todo;

import com.in28minutes.rest.webservices.restfulwebservices.todo.repository.TodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoJpaResource {

    private TodoService todoService;

    private TodoRepository todoRepository;

    public TodoJpaResource(TodoService todoService, TodoRepository todoRepository) {
        this.todoService = todoService;
        this.todoRepository = todoRepository;
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username) {
        return todoRepository.findByUsername(username);
    }

    @GetMapping("/users/{username}/todo/{id}")
    public Todo retrieveTodo(@PathVariable int id) {
        return todoRepository.findById(id).get();
    }

    @DeleteMapping("/users/{username}/todo/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id) {
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/todo/{id}")
    public Todo updateTodo(@PathVariable String username, @PathVariable int id
            , @RequestBody Todo todo) {
//        todoService.updateTodo(todo);
        todoRepository.save(todo);
        return todo;
    }

    @PostMapping("/users/{username}/todo")
    public Todo createTodo(@PathVariable String username, @RequestBody Todo todo) {
        todo.setUsername(username);
        todo.setId(null);
        return todoRepository.save(todo);

//        return todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
    }
}
