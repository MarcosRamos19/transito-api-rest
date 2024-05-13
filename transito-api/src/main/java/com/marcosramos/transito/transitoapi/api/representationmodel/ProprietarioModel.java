package com.marcosramos.transito.transitoapi.api.representationmodel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProprietarioModel {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Marcos")
    private String nome;

    @Schema(example = "marcos@outlook.com")
    private String email;

    @Schema(example = "11 9999999999")
    private String telefone;

}
