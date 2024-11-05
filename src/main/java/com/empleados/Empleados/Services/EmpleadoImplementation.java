package com.empleados.Empleados.Services;

import com.empleados.Empleados.Model.User;
import com.empleados.Empleados.Model.UserRequest;
import com.empleados.Empleados.Repository.EmpleadoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoImplementation implements EmpleadoService {
    protected String exchange = "default";
    protected String queue = "user.queue";

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    protected RabbitTemplate rabbitTemplate;

    public List<User> getUsers() {
        return empleadoRepository.findAll();
    }

    public List<User> getUsersNLState() {
        return empleadoRepository.findByEstadoIgnoreCase("Nuevo Leon");
    }

    public List<String> getStates() {
        return new ArrayList<>(empleadoRepository.findEstado().stream().map(s -> new JSONObject(s).getString("estado")).collect(Collectors.toSet()));
    }

    public void postUser(UserRequest userRequest) {
        try{
            rabbitTemplate.convertAndSend(exchange,queue,userRequest);
            System.out.printf("\nUser %s sent to  \"%s\" queue with exchange \"%s\"",userRequest.nombre(),queue,exchange);
        }catch (Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }


@RabbitListener(queues = "user.queue")
public void queueMessage(UserRequest userRequest){
    System.out.println("hellooooooo!!!!!");
    User newUser = new User(userRequest.nombre(), userRequest.estado(), userRequest.edad(),
            userRequest.salarioDiario() * userRequest.diasTrabajados());

    System.out.println(newUser);
    empleadoRepository.save(newUser);
    }


}