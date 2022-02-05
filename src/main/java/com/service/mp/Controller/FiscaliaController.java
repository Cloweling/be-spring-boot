package com.service.mp.Controller;

import javax.validation.Valid;

import com.service.mp.model.Fiscalia;
import com.service.mp.repository.FiscaliaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fiscalia")
public class FiscaliaController {

    @Autowired
    FiscaliaRepository fiscaliaRepository;

    @PostMapping("/add")
    Fiscalia addFiscalia(@Valid @RequestBody Fiscalia fiscalia) {
        return fiscaliaRepository.save(fiscalia);
    }

}
