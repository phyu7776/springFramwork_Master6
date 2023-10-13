package com.in28minutes.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();

    private static int todosCount = 0;

    static {
        todos.add(new Todo(++todosCount, "phy","Learn AWS", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "phy","Learn DevOps", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++todosCount, "phy","Learn Development", LocalDate.now().plusYears(3), false));
    }

    public List<Todo> findByUserName(String name) {
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(name);
        return todos.stream().filter(predicate).toList();
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        return todos.stream().filter(predicate).findFirst().get();
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
        Todo todo = new Todo(++todosCount, username, description, targetDate, done);
        todos.add(todo);
    }

    public void deleteById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}
