package com.marcosramos.transito.transitoapi.api.controller.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Apreensão de veículos")
public interface ApreensaoVeiculoControllerOpenApi {

    @Operation(summary = "Apreende um veículo por Id", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Id de veículo inválido", content = @Content(schema = @Schema))
    })
    public void apreender(@Parameter(description = "Id de um veículo", example = "1", required = true)Long veiculoId);
    @Operation(summary = "Remove uma apreensão de veículo por Id", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Id de veículo inválido", content = @Content(schema = @Schema))
    })
    public void removerApreensao(@Parameter(description = "Id de um veículo", example = "1", required = true)Long veiculoId);
    }


