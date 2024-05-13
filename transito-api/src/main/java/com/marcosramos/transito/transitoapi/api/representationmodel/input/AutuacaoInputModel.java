package com.marcosramos.transito.transitoapi.api.representationmodel.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AutuacaoInputModel {

    @Schema(example = "Ultrapssou sinal vermelho")
    @NotBlank
    private String descricao;

    @Schema(example = "1200.50")
    @NotNull
    @Positive
    private BigDecimal valorMulta;
}
