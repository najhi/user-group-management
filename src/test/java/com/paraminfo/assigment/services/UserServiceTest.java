package com.paraminfo.assigment.services;

import com.paraminfo.assigment.entities.User;
import com.paraminfo.assigment.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setEmail("testuser@example.com");

        Mockito.when(userRepository.save(user)).thenReturn(user);

        User created = userService.createUser(user);
        assertThat(created.getUsername()).isSameAs(user.getUsername());
    }

    @Test
    public void testGetAllUsers() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setEmail("testuser@example.com");

        List<User> users = Collections.singletonList(user);

        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<User> result1 = userService.getAllUsers();
        List<User> result2 = userService.getAllUsers();

        assertThat(result1).isEqualTo(users);
        assertThat(result2).isEqualTo(users);

        // Verify that repository is called only once due to caching
        verify(userRepository, times(1)).findAll();
    }
}
