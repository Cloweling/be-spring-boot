package com.service.mp.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.service.mp.model.Fiscalia;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FiscaliaRepository extends CrudRepository<Fiscalia, Integer> {
    List<Fiscalia> findByNameContaining(String name);

    List<Fiscalia> findByNameContains(String name);

    List<Fiscalia> findByNameIsContaining(String name);

    @Query(value = "select count(ubicacion_id) from fiscalia where ubicacion_id = ?1", nativeQuery = true)
    int countByUbicacionId(int ubicacionId);

    @Modifying
    @Transactional
    @Query(value = "update fiscalia set name = ?1, telefono = ?2, ubicacion_id = ?3 where id = ?4", nativeQuery = true)
    void setFiscalia(String name, String telefono, int ubicacionId, int id);
}
