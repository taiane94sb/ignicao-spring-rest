package com.taianesb.transito.api.controller;

import com.taianesb.transito.domain.exception.NegocioException;
import com.taianesb.transito.domain.model.Veiculo;
import com.taianesb.transito.domain.repository.VeiculoRepository;
import com.taianesb.transito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final RegistroVeiculoService registroVeiculoService;
    private final VeiculoRepository veiculoRepository;

    @GetMapping
    public List<Veiculo> listar() {
        return veiculoRepository.findAll();
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<Veiculo> buscar(@PathVariable Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo cadastrar(@Valid @RequestBody Veiculo veiculo) {
        return registroVeiculoService.cadastrar(veiculo);
    }

}
