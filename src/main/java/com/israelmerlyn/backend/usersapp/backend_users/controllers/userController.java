package com.israelmerlyn.backend.usersapp.backend_users.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.israelmerlyn.backend.usersapp.backend_users.models.User;
import com.israelmerlyn.backend.usersapp.backend_users.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")

public class userController {

    @Autowired
    private UserServices services;

    @GetMapping
    public List<User> list() {
        return services.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<User> userOptional = services.findById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {
       if (result.hasErrors()) {
        return  Validation(result);
       }
        return ResponseEntity.status(HttpStatus.CREATED).body(services.save(user));
    }

   

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody User user,BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return  Validation(result);
        }
        Optional<User> o = services.update(user, id);
        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove (@PathVariable Long id){
        Optional<User> o = services.findById(id);

        if (o.isPresent()) {
            services.remove(id);
             return  ResponseEntity.noContent().build();
        }
        return  ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> Validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
        
    }

}
