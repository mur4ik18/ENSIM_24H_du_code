package org.example.api.api.service;

import org.example.api.api.model.Role;
import org.example.api.api.model.User;
import org.example.api.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return repository.findById(id);
    }
}