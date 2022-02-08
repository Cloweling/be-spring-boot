package com.service.mp.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.service.mp.model.Ubicacion;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UbicacionRepository extends CrudRepository<Ubicacion, Integer> {
    List<Ubicacion> findByNameContaining(String name);

    List<Ubicacion> findByNameContains(String name);

    List<Ubicacion> findByNameIsContaining(String name);

    @Modifying
    @Transactional
    @Query(value = "update ubicacion set name = ?1 where id = ?2", nativeQuery = true)
    void setUbicacion(String name, int id);
}
