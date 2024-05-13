package com.marcosramos.transito.transitoapi.api.representationmodel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProprietarioResumoModel {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Marcos")
    private String nome;
}
