package com.paraminfo.assigment.services;

import com.paraminfo.assigment.entities.User;
import com.paraminfo.assigment.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final CaffeineCache cache;

    @Autowired
    public UserService(UserRepository userRepository, @Qualifier("userCache") CaffeineCache cache) {
        this.userRepository = userRepository;
        this.cache = cache;
    }

    public User createUser(User user) {
        cache.clear();
        return userRepository.save(user);
    }

    @Cacheable(value = "users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}