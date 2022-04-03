package com.javatechie.api;

import com.javatechie.api.dto.UserRequest;
import com.javatechie.api.entity.User;
import com.javatechie.api.exception.UserNotFoundException;
import com.javatechie.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.Set;

@SpringBootApplication
@RestController
@RequestMapping("/users")
public class FiledExceptionHandingApplication {

    @Autowired
    private UserRepository repository;


    @PostMapping("/signup")
    public ResponseEntity<User> addUser(@RequestBody @Valid UserRequest userRequest) {
        User user = repository.save(User.
                build(0, userRequest.getName(), userRequest.getEmail(), userRequest.getMobile(),
                        userRequest.getGender(), userRequest.getAge(), userRequest.getNationality()));
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) throws UserNotFoundException {
        User user = repository.findByUserId(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            throw new UserNotFoundException("User not found with id " + id);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(FiledExceptionHandingApplication.class, args);
    }

}
