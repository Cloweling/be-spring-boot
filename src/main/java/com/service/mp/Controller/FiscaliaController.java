package com.service.mp.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.service.mp.model.Fiscalia;
import com.service.mp.repository.FiscaliaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/api/v1/fiscalia")
public class FiscaliaController {

    @Autowired
    FiscaliaRepository fiscaliaRepository;

    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:3000")
    ResponseEntity<Object> addFiscalia(@Valid @RequestBody Fiscalia fiscalia) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date());
        map.put("status", 200);
        map.put("message", "Guardado correctamente.");

        // try {
            map.put("data", fiscaliaRepository.save(fiscalia));
        // } catch (Exception e) {
        //     map.put("status", 400);
        //     map.put("message", "Error al guardar.");
        //     map.put("data", null);

        //     return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        // }

        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    ResponseEntity<Object> getFiscalia(@RequestParam(value = "name", required = false) String name) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date());
        map.put("status", 200);
        map.put("message", null);

        try {
            if (name == null || name.isEmpty()) {
                map.put("data", (List<Fiscalia>) fiscaliaRepository.findAll());
                return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
            }

            map.put("data", (List<Fiscalia>) fiscaliaRepository.findByNameContaining(name));
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            map.put("status", 500);
            map.put("message", "Error al obtener los datos");
            map.put("data", null);
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    ResponseEntity<Object> editUbicacion(@Valid @RequestBody Fiscalia editFiscalia, @PathVariable int id) {
        Optional<Fiscalia> optionalFiscalia = fiscaliaRepository.findById(id);

        HashMap<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date());
        map.put("status", 200);
        map.put("message", "Editado correctamente.");

        if (optionalFiscalia.isPresent() == false) {
            map.put("status", 400);
            map.put("message", "No existe la fiscalia.");
            map.put("data", null);

            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

        Fiscalia fiscalia = fiscaliaRepository.findById(id).get();
        fiscalia.setName(editFiscalia.getName());
        fiscalia.setTelefono(editFiscalia.getTelefono());

        try {
            fiscaliaRepository.setFiscalia(fiscalia.getName(), fiscalia.getTelefono(),
                    editFiscalia.getUbicacion().getId(),
                    fiscalia.getId());
        } catch (Exception e) {
            map.put("status", 400);
            map.put("message", "No existe la ubicacion.");
            map.put("data", null);

            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

        fiscalia.getUbicacion().setId(editFiscalia.getUbicacion().getId());
        map.put("data", fiscalia);

        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    ResponseEntity<Object> deleteFiscalia(@PathVariable int id) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date());

        try {
            map.put("status", 200);
            map.put("delete", true);
            map.put("message", "Se elimino exitosamente.");

            fiscaliaRepository.deleteById(id);
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);

        } catch (Exception e) {
            map.put("status", 400);
            map.put("delete", false);
            map.put("message", "No existe la fiscalia.");

            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }
}
