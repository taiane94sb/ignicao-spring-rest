package com.taianesb.transito.domain.service;

import com.taianesb.transito.domain.exception.EntidadeNaoEncontradaException;
import com.taianesb.transito.domain.exception.NegocioException;
import com.taianesb.transito.domain.model.Proprietario;
import com.taianesb.transito.domain.model.StatusVeiculo;
import com.taianesb.transito.domain.model.Veiculo;
import com.taianesb.transito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {

    private final RegistroProprietarioService registroProprietarioService;
    private final VeiculoRepository veiculoRepository;

    public Veiculo buscar(Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Veículo não encontrado"));
    }

    @Transactional
    public Veiculo cadastrar(Veiculo veiculo) {
        if (veiculo.getId() != null) {
            throw new NegocioException("Veículo a ser cadastrado não deve possuir um código");
        }

        boolean placaEmUso = veiculoRepository.findByPlaca(veiculo.getPlaca())
                        .filter(v -> !v.equals(veiculo))
                        .isPresent();

        if (placaEmUso) {
            throw new NegocioException("Já existe um veículo cadastrado com esta placa");
        }

        Proprietario proprietario = registroProprietarioService.buscar(veiculo.getProprietario().getId());

        veiculo.setProprietario(proprietario);
        veiculo.setStatus(StatusVeiculo.REGULAR);
        veiculo.setDataCadastro(OffsetDateTime.now());

        return veiculoRepository.save(veiculo);
    }
}
