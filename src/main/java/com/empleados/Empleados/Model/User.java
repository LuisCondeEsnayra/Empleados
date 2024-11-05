package com.empleados.Empleados.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Empleados")
public record User(
        String nombre,
        String estado,
        String edad,
        Float salarioFinal
) {

}
