package com.service.mp.Controller;

import javax.validation.Valid;

import com.service.mp.model.Ubicacion;
import com.service.mp.repository.UbicacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ubicacion")
public class UbicacionController {
    @Autowired
    private UbicacionRepository ubicacionRepository;

    @PostMapping("/add")
    Ubicacion addUbicacion(@Valid @RequestBody Ubicacion ubicacion) {
        return ubicacionRepository.save(ubicacion);

    }
}
