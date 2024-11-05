package com.empleados.Empleados.Controller;

import com.empleados.Empleados.Model.User;
import com.empleados.Empleados.Model.UserRequest;
import com.empleados.Empleados.Services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmpleadosController {

    @Autowired
    EmpleadoService empleados;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        try {
            List<User> list = empleados.getUsers();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create user. Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/NuevoLeon")
    public ResponseEntity<?> getUsersNLState() {
        try {
            List<User> list = empleados.getUsersNLState();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create user. Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/estados")
    public ResponseEntity<?> getStates() {
        try {
            List<String> list = empleados.getStates();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create user. Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping("/users")
    public ResponseEntity<?> postUser(@RequestBody UserRequest userRequest) {
        try {
            empleados.postUser(userRequest);
            return new ResponseEntity<>(
                    new HashMap<String, Object>() {{
                        put("success", true);
                        put("message", "Usuario enviado para creacion");
                    }},
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create user. Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
}
