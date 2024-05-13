package com.marcosramos.transito.transitoapi.api.representationmodel.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProprietarioIdInput {

    @Schema(example = "1")
    @NotNull
    private Long id;
}
