package org.example.api.api.service;

import org.example.api.api.model.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findById(Integer id);
}
