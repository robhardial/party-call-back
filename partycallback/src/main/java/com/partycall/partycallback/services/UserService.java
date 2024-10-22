package com.partycall.partycallback.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partycall.partycallback.models.Event;
import com.partycall.partycallback.models.User;
import com.partycall.partycallback.repositiories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Finds a User by their ID.
     *
     * @param id the ID of the User to find
     * @return the User with the specified ID, or null if no User is found
     */
    public User findUserById(int id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {

            return user.get();
        }

        return null;
    }

    public User findUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {

            return user.get();
        }

        return null;
    }

    /**
     * Saves the given user into the repository.
     *
     * @param user The user to be saved.
     * @return The saved user.
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Edits an existing user in the system with the specified ID.
     *
     * @param id   The ID of the user to edit.
     * @param user The updated user object with the new email and password.
     * @return The edited user object.
     */
    public User editUser(int id, User user) {
        Optional<User> existingUserOptional = userRepository.findById(id);

        if (existingUserOptional.isPresent()) {

            User existingUser = existingUserOptional.get();

            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }
            if (user.getPassword() != null) {
                existingUser.setPassword(user.getPassword());
            }
            if (user.getFirstName() != null) {
                existingUser.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null) {
                existingUser.setLastName(user.getLastName());
            }
            if (user.getEventsOrganized() != null) {
                existingUser.setEventsOrganized(user.getEventsOrganized());
            }
            if (user.getTickets() != null) {
                existingUser.setTickets(user.getTickets());
            }

            return userRepository.save(existingUser);
        } else {
            return userRepository.save(user);
        }
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to be deleted
     */
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

}
