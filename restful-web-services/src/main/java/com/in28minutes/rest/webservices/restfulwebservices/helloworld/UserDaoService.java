package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>(){
        {
            add(new User(1, "Adam", LocalDate.now().minusYears(30)));
            add(new User(2, "Eve", LocalDate.now().minusYears(25)));
            add(new User(3, "Jim", LocalDate.now().minusYears(20)));
        }};

    public List<User> findAll() {
        return users;
    }

    public void save(User user) {
        users.add(user);
    }

//    public User findOne(int id) {
//
//    }
}
