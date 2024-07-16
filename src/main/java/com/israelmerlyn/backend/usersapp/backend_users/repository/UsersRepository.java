package com.israelmerlyn.backend.usersapp.backend_users.repository;

import org.springframework.data.repository.CrudRepository;

import com.israelmerlyn.backend.usersapp.backend_users.models.User;

public interface  UsersRepository  extends  CrudRepository<User, Long>{

}
