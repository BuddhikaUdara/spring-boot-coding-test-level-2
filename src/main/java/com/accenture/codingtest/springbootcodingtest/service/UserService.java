package com.accenture.codingtest.springbootcodingtest.service;

import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) throws Exception {
        Optional<User> currentUser = userRepository.findById(id);
        if (currentUser.isPresent())
            return currentUser.get();
        else
            throw new Exception("User Not Found");

    }

    public User createUser(User user) throws Exception {
        Optional<User> userByUsername = userRepository.findUserByUsername(user.getUsername());
        if (userByUsername.isPresent())
            throw new Exception("User Registered");
        else {
            userRepository.save(user);
            return user;
        }
    }

    public User updateUser(UUID id, User user) throws Exception {
        Optional<User> _user = userRepository.findById(id);
        if (_user.isPresent()) {
                User currentUser = _user.get();
                currentUser.setUsername(user.getUsername());
                currentUser.setPassword(user.getPassword());
                userRepository.save(currentUser);
                return currentUser;           
        } else
            throw new Exception("User Not Found");
    }

    public User patchUser(UUID id, User user) throws Exception {
        User currentUser = null;
        Optional<User> _user = userRepository.findById(id);
        if (_user.isPresent()) {
        	currentUser = _user.get();
            if (StringUtils.hasLength(user.getUsername())) {
                currentUser.setUsername(user.getUsername());
                userRepository.save(currentUser);
            }
            if (StringUtils.hasLength(user.getPassword())) {
            	currentUser.setPassword(user.getPassword());
            	userRepository.save(currentUser);
            }
        } else throw new Exception("User Not Found");
                   
        return currentUser;
    }

    public void deleteUser(UUID id) throws Exception {
        Optional<User> currentUser = userRepository.findById(id);
        if (currentUser.isPresent())
            userRepository.deleteById(id);
        else
            throw new Exception("User Not Found");

    }
}
