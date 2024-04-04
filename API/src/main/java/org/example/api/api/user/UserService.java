package org.example.api.api.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<User> findByUsername(String email) {
        return repository.findByUsername(email);
    }

    @Override
    public ArrayList<UserInfoResponse> findAll() {
        List<User> users = repository.findAll();
        ArrayList<UserInfoResponse> userInfoResponses = new ArrayList<UserInfoResponse>();
        for (User user : users) {
            userInfoResponses.add(UserInfoResponse.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .role(user.getRole().toString())
                    .build());
        }
        System.out.println(userInfoResponses);
        return userInfoResponses;
    }
}