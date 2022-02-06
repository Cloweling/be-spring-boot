package com.service.mp.repository;

import java.util.List;

import com.service.mp.model.Ubicacion;

import org.springframework.data.repository.CrudRepository;

public interface UbicacionRepository extends CrudRepository<Ubicacion, Integer> {
    List<Ubicacion> findByNameContaining(String name);

    List<Ubicacion> findByNameContains(String name);

    List<Ubicacion> findByNameIsContaining(String name);
}
