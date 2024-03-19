package org.example.api.api.user;

import java.util.Optional;

public interface IUserService {
    Optional<User> findById(Integer id);
}
