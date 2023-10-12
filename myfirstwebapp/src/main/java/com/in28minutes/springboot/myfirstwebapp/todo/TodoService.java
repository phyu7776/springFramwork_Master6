package com.in28minutes.springboot.myfirstwebapp.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();

    static {
        todos.add(new Todo(1, "phy","Learn AWS", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(1, "phy","Learn DevOps", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(1, "phy","Learn Development", LocalDate.now().plusYears(3), false));
    }

    public List<Todo> findByUserName(String name) {
        return todos;
    }
}
