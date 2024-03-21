package org.example.api.api.user;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String email);
}
