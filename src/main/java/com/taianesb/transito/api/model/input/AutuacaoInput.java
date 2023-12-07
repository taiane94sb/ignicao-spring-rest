package com.taianesb.transito.api.model.input;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Getter
@Setter
public class AutuacaoInput {

    @NotBlank
    private String descricao;

    @NotNull
    @Positive
    private BigDecimal valorMulta;
}