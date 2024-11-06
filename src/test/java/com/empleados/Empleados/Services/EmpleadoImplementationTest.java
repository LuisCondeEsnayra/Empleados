package com.empleados.Empleados.Services;

import com.empleados.Empleados.Model.User;
import com.empleados.Empleados.Model.UserRequest;
import com.empleados.Empleados.Repository.EmpleadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EmpleadoImplementationTest {

    @InjectMocks
    private EmpleadoImplementation empleadoImplementation;

    @Mock
    private EmpleadoRepository empleadoRepository;


    @Test
    void testGetUsers() {
        List<User> users = Arrays.asList(
                new User("John", "Nuevo Leon", "25", 2000F),
                new User("Jane", "Jalisco", "28", 2500F)
        );
        when(empleadoRepository.findAll()).thenReturn(users);
        List<User> result = empleadoImplementation.getUsers();

        assertEquals(2, result.size());
    }

    @Test
    void testGetUsersNLState() {
        List<User> usersNL = Arrays.asList(
                new User("Carlos", "Nuevo Leon", "30", 3000F),
                new User("Luis", "Nuevo Leon", "35", 3500F)
        );
        when(empleadoRepository.findByEstadoIgnoreCase("Nuevo Leon")).thenReturn(usersNL);

        List<User> result = empleadoImplementation.getUsersNLState();

        assertEquals(2, result.size());
    }

    @Test
    void testGetStates() {
        List<String> states = Arrays.asList(
                "{\"estado\":\"Nuevo Leon\"}",
                "{\"estado\":\"Jalisco\"}",
                "{\"estado\":\"Nuevo Leon\"}"
        );
        when(empleadoRepository.findEstado()).thenReturn(states);
        List<String> result = empleadoImplementation.getStates();
        assertEquals(2, result.size());
    }



    @Test
    void testQueueMessage() {
        UserRequest userRequest = new UserRequest("Pedro", "Nuevo Leon", "30", 1800F, 20);

        empleadoImplementation.queueMessage(userRequest);

        verify(empleadoRepository, times(1)).save(any(User.class));
    }
}
