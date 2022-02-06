package com.service.mp.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.service.mp.model.Ubicacion;
import com.service.mp.repository.FiscaliaRepository;
import com.service.mp.repository.UbicacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ubicacion")
public class UbicacionController {
    @Autowired
    private UbicacionRepository ubicacionRepository;

    @Autowired
    private FiscaliaRepository fiscaliaRepository;

    @PostMapping("/add")
    ResponseEntity<Object> addUbicacion(@Valid @RequestBody Ubicacion ubicacion) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date());
        map.put("status", 200);
        map.put("message", "Guardado correctamente.");

        try {
            map.put("data", ubicacionRepository.save(ubicacion));
        } catch (Exception e) {
            map.put("status", 400);
            map.put("message", "Error al guardar.");
            map.put("data", null);

            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @PutMapping("/edit/{id}")
    ResponseEntity<Object> editUbicacion(@Valid @RequestBody Ubicacion editUbicacion, @PathVariable int id) {
        Optional<Ubicacion> optionalUbicacion = ubicacionRepository.findById(id);

        HashMap<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date());
        map.put("status", 200);
        map.put("message", "Editado correctamente.");

        if (optionalUbicacion.isPresent() == false) {
            map.put("status", 400);
            map.put("ubiacion", null);
            map.put("message", "No existe la ubicacion.");
            map.put("data", null);

            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

        Ubicacion ubicacion = ubicacionRepository.findById(id).get();

        ubicacion.setName(editUbicacion.getName());
        map.put("data", ubicacion);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @GetMapping
    ResponseEntity<Object> getUbicacion(@RequestParam(value = "name", required = false) String name) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date());
        map.put("status", 200);
        map.put("message", null);

        try {
            if (name == null || name.isEmpty()) {
                map.put("data", (List<Ubicacion>) ubicacionRepository.findAll());
                return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
            }

            map.put("data", (List<Ubicacion>) ubicacionRepository.findByNameContaining(name));
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            map.put("status", 500);
            map.put("message", "Error al obtener los datos");
            map.put("data", null);
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Object> deleteUbicacion(@PathVariable int id) {
        boolean isExistsInFiscalia = fiscaliaRepository.countByUbicacionId(id) != 0;

        HashMap<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date());
        map.put("status", 400);
        map.put("delete", false);
        map.put("message", "No se puede eliminar porque ya existe.");

        try {
            if (isExistsInFiscalia) {
                return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
            }

            ubicacionRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            map.put("message", "No existe esa ubicacion.");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

        }

        map.put("status", 200);
        map.put("delete", true);
        map.put("message", "Se elimino exitosamente.");

        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
}
