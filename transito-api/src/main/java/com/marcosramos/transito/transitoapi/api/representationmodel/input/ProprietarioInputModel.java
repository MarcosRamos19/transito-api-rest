package com.marcosramos.transito.transitoapi.api.representationmodel.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProprietarioInputModel {

    @Schema(example = "Marcos")
    @NotBlank
    @Size(max = 60)
    private String nome;

    @Schema(example = "1marcos@outlook.com")
    @NotBlank
    @Size(max = 255)
    @Email
    private String email;

    @Schema(example = "11 999999999")
    @NotBlank
    @Size(max = 20)
    private String telefone;

}
