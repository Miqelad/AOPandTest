package com.miki.testExample.manager;

import com.miki.testExample.entity.User;
import com.miki.testExample.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserManagerTest {


    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserManager userManager;

    @Captor
    ArgumentCaptor<Integer> idCaptor;

    @Test
    void getAll_shouldGetAllUser_whenCalledMethod() {
        //Arrange
        //Можно мокать его, чтобы не проверять сам объект, нам его не обязательно создавать
        //новый объект,хоть и можно
        User user = mock(User.class);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        Mockito.when(userRepository.findAll()).thenReturn(userList);

        //Act
        List<User> userRepositoryAll = userRepository.findAll();
        List<User> all = userManager.getAll();


        //Assert
        assertNotNull(userRepositoryAll);
        assertEquals(all, userRepositoryAll);

    }

    @Test
    void getById_shouldGetUserById() {
        final Optional<User> userOptional= Optional.ofNullable(mock(User.class));
        User user = mock(User.class);
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        //Обязательно нужно его вызвать, что перехватить значение
        userRepository.findById(1);
        //Capture
        Mockito.verify(userRepository).findById(idCaptor.capture());

        Integer idCapture = idCaptor.getValue();
        User byId = userManager.findById(1);


        assertInstanceOf(Integer.class,idCapture);



    }
}