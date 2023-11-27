package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {

    private static int usersCnt = 0;

    private static List<User> users = new ArrayList<>(){
        {
            add(new User(++usersCnt, "Adam", LocalDate.now().minusYears(30)));
            add(new User(++usersCnt, "Eve", LocalDate.now().minusYears(25)));
            add(new User(++usersCnt, "Jim", LocalDate.now().minusYears(20)));
        }};

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        user.setId(++usersCnt);
        users.add(user);
        return user;
    }

    public User fildOne(int id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteById(int id) {
//        return users.stream()
//                .filter(user -> user.getId().equals(id))
//                .findFirst()
//                .orElse(null);

        users.removeIf(user -> user.getId().equals(id));
    }

}
