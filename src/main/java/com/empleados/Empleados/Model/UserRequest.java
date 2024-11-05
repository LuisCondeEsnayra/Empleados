package com.empleados.Empleados.Model;

public record UserRequest(

        String nombre,
        String estado,
        String edad,
        Float salarioDiario,
        Integer diasTrabajados) {
}

