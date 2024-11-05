package com.empleados.Empleados.Repository;

import com.empleados.Empleados.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EmpleadoRepository extends MongoRepository<User, Integer> {

    List<User> findByEstadoIgnoreCase(String estado);

    @Query(value = "{}", fields = "{ 'estado' :1 }")
    List<String> findEstado();

}
