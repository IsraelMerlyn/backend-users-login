package com.israelmerlyn.backend.usersapp.backend_users.services;

import java.util.List;
import java.util.Optional;

import com.israelmerlyn.backend.usersapp.backend_users.models.User;

public interface UserServices {

    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    Optional<User>  update(User user, Long id);

    void remove(Long id)
;
}
