package com.marcosramos.transito.transitoapi.api.representationmodel;

import com.marcosramos.transito.transitoapi.domain.model.StatusVeiculo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class VeiculoModel {

    @Schema(example = "1")
    private Long id;

    private ProprietarioResumoModel proprietario;

    @Schema(example = "Mercedes")
    private String marca;

    @Schema(example = "c180")
    private String modelo;

    @Schema(example = "AAA1111/AAA1A11")
    private String placa;

    private StatusVeiculo status;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;


}
