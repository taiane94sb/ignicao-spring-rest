package com.taianesb.transito.api.controller;

import com.taianesb.transito.domain.model.Proprietario;
import com.taianesb.transito.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class ProprietarioController {

    private final ProprietarioRepository proprietarioRepository;

    @GetMapping("/proprietarios")
    public List<Proprietario> listar() {
        return proprietarioRepository.findAll();
    }

    @GetMapping("/proprietarios/findByNome")
    public List<Proprietario> findByName() {
        return proprietarioRepository.findByNome("Taiane");
    }

    @GetMapping("/proprietarios/findByNomeContaining")
    public List<Proprietario> findByNameContaining() {
        return proprietarioRepository.findByNomeContaining("the");
    }
}
