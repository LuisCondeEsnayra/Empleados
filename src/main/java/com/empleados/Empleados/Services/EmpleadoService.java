package com.empleados.Empleados.Services;

import com.empleados.Empleados.Model.User;
import com.empleados.Empleados.Model.UserRequest;

import java.util.List;

public interface EmpleadoService {
    List<User> getUsers();

    List<User> getUsersNLState();

    List<String> getStates();

    void postUser(UserRequest userRequest);
}
