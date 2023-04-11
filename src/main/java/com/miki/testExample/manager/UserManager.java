package com.miki.testExample.manager;

import com.miki.testExample.entity.User;
import com.miki.testExample.exceptions.NotFoundUser;
import com.miki.testExample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager {
    private final UserRepository userRepository;

    @Autowired
    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(NotFoundUser::new);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(Integer id) {
        User user = userRepository.findById(id).orElseThrow(NotFoundUser::new);
        userRepository.delete(user);
    }
}
