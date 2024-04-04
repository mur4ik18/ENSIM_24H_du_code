package org.example.api.api.user;

import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String email);

    @Query("SELECT * FROM _user")
    ArrayList<UserInfoResponse> findAll();
}
