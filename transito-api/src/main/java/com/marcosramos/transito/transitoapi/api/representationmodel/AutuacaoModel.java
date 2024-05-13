package com.marcosramos.transito.transitoapi.api.representationmodel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class AutuacaoModel {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Ultrapassou sinal vermelho")
    private String descricao;

    @Schema(example = "1200.50")
    private BigDecimal valorMulta;

    private OffsetDateTime dataOcorrencia;
}
