package com.empleados.Empleados;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class EmpleadosApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmpleadosApplication.class, args);
    }

}
