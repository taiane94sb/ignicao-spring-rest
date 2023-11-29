package com.taianesb.transito.api.controller;

import com.taianesb.transito.domain.model.Proprietario;
import com.taianesb.transito.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

    private final ProprietarioRepository proprietarioRepository;

    @GetMapping
    public List<Proprietario> listar() {
        return proprietarioRepository.findAll();
    }

    @GetMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> buscar(@PathVariable Long proprietarioId) {
        Optional<Proprietario> proprietario = proprietarioRepository.findById(proprietarioId);

        if (proprietario.isPresent()) {
            return ResponseEntity.ok(proprietario.get());
        }

        return ResponseEntity.notFound().build();
    }

}
