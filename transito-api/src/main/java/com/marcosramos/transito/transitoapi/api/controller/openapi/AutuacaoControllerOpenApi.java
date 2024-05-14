package com.marcosramos.transito.transitoapi.api.controller.openapi;

import com.marcosramos.transito.transitoapi.api.representationmodel.AutuacaoModel;
import com.marcosramos.transito.transitoapi.api.representationmodel.input.AutuacaoInputModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


import java.util.List;

@Tag(name = "Autuações dos veículos")
public interface AutuacaoControllerOpenApi {
    @Operation(summary = "Registra uma autuação de um veículo por Id", responses = {
            @ApiResponse(responseCode = "400", description = "Id de veículo inválido", content = @Content(schema = @Schema))
    })
    public AutuacaoModel registrar(@Parameter(description = "Id de um veículo", example = "1", required = true)Long veiculoId,
                                   @RequestBody(description = "Representação de uma autuação", required = true)AutuacaoInputModel autuacaoInputModel);

    @Operation(summary = "Lista as autuações de um veículo por Id")
    public List<AutuacaoModel> listar(Long veiculoId);

}
