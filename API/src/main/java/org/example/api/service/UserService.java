package org.example.api.service;

import org.example.api.api.model.Role;
import org.example.api.api.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private List<User> userList;

    public UserService() {
        userList = new ArrayList<>();

        User user1 = new User(1,"Ida","Ida", "Papich", 32, "ida@mail.com","ABC", Role.Participant);
        User user2 = new User(2,"Hans","Hans", "Papich", 26, "hans@mail.com","ABC", Role.Participant);
        User user3 = new User(3,"Lars","Lars", "Papich", 45, "lars@mail.com","ABC", Role.Participant);
        User user4 = new User(4,"Ben","Ben", "Papich", 32, "ben@mail.com","ABC", Role.Participant);
        User user5 = new User(5,"Eva","Eva", "Papich", 59, "eva@mail.com","ABC", Role.Participant);

        userList.addAll(Arrays.asList(user1,user2,user3,user4,user5));
    }
    public Optional<User> getUser(Integer id) {
        Optional<User> optional = Optional.empty();
        for (User user: userList) {
            if(id == user.getId()){
                optional = Optional.of(user);
                return optional;
            }
        }
        return optional;
    }
}
