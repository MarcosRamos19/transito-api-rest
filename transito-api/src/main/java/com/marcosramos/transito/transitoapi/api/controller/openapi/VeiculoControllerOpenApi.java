package com.marcosramos.transito.transitoapi.api.controller.openapi;

import com.marcosramos.transito.transitoapi.api.representationmodel.VeiculoModel;
import com.marcosramos.transito.transitoapi.api.representationmodel.input.VeiculoInputModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Veículos", description = "Gerencia veículos")
public interface VeiculoControllerOpenApi {
    @Operation(summary = "Lista os véiculos")
    public List<VeiculoModel> listar();
    @Operation(summary = "Busca um veículo por Id", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Id de veículo inválido", content = @Content(schema = @Schema))
    })
    public ResponseEntity<VeiculoModel> buscar(@Parameter(description = "Id de um veículo", example = "1", required = true) Long veiculoId);
    @Operation(summary = "Cadastra um novo veículo", responses = {
            @ApiResponse(responseCode = "400", description = "Representação de veículo inválida", content = @Content(schema = @Schema))
    })
    public VeiculoModel cadastrar(@RequestBody(description = "Representação de um novo veículo", required = true) VeiculoInputModel veiculoInput);

    }


