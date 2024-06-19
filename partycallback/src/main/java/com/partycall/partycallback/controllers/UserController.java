package com.partycall.partycallback.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.partycall.partycallback.models.User;
import com.partycall.partycallback.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Retrieves all users from the database.
     *
     * @return A ResponseEntity containing a list of User objects representing all
     *         users in the database.
     */
    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    /**
     * Retrieves a User from the database based on the provided ID.
     *
     * @param id the ID of the User to retrieve
     * @return a ResponseEntity containing the requested User if found, or
     *         HttpStatus.OK if successful
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    /**
     * Creates a new user with the provided user information.
     *
     * @param user The user object containing the user information.
     * @return A ResponseEntity object with the created user and HTTP status code
     *         201 (Created).
     */
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.saveUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    /**
     * Edits an existing user in the system with the specified ID.
     *
     * @param id   The ID of the user to edit.
     * @param user The updated user object with the new email and password.
     * @return The edited user object.
     */
    @PutMapping("/user/{id}")
    public ResponseEntity<User> editUser(@PathVariable int id, @RequestBody User user) {
        User updatedUser = userService.editUser(id, user);
        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to be deleted
     * @return a response entity indicating success with no content
     */
    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

}
