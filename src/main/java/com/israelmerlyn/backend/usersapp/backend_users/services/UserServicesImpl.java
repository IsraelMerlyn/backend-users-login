package com.israelmerlyn.backend.usersapp.backend_users.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.israelmerlyn.backend.usersapp.backend_users.models.User;
import com.israelmerlyn.backend.usersapp.backend_users.repository.UsersRepository;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UsersRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<User> update(User user, Long id) {
        Optional<User> o = this.findById(id);
        User userOptional = null;
        if (o.isPresent()) {
            User userdb = o.orElseThrow();
            userdb.setUsername(user.getUsername());
            userdb.setEmail(user.getEmail());
            userdb.setPassword(user.getPassword());
            userOptional = this.save(userdb);
        }
        return Optional.ofNullable(userOptional);
    }

}
