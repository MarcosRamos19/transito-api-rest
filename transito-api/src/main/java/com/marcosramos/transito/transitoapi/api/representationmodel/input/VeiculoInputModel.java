package com.marcosramos.transito.transitoapi.api.representationmodel.input;

import com.marcosramos.transito.transitoapi.domain.model.Proprietario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoInputModel {

    @Schema(example = "Mercedes")
    @NotBlank
    @Size(max =20)
    private String marca;

    @Schema(example = "c180")
    @NotBlank
    @Size(max =20)
    private String modelo;

    @Schema(example = "AAA1111/AAA1A11")
    @NotBlank
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}") //validação formato de placas antigas e novas
    private String placa;


    @Valid
    @NotNull
    private ProprietarioIdInput proprietario;
}
