package com.marcosramos.transito.transitoapi.api.controller.openapi;

import com.marcosramos.transito.transitoapi.api.representationmodel.ProprietarioModel;
import com.marcosramos.transito.transitoapi.api.representationmodel.input.ProprietarioInputModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Proprietários", description = "Gerencia propretários")
public interface ProprietarioControllerOpenApi {

    @Operation(summary = "Lista os proprietários")
    public List<ProprietarioModel> listar();

    @Operation(summary = "Busca um proprietário por Id", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Id de proprietário inválido", content = @Content(schema = @Schema))
    })
    public ResponseEntity<ProprietarioModel> buscar(@Parameter(description = "Id de um proprietário", example = "1", required = true)Long proprietarioId);

    @Operation(summary = "Cadastra um novo proprietário", responses = {
            @ApiResponse(responseCode = "400", description = "Representação de proprietário inválida", content = @Content(schema = @Schema))
    })
    public ProprietarioModel adicionar(@RequestBody(description = "Representação de um proprietário", required = true) ProprietarioInputModel proprietarioInputModel);

    @Operation(summary = "Atualiza as informações de um proprietário por Id", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Representação de proprietário inválida", content = @Content(schema = @Schema))
    })
    public ResponseEntity<ProprietarioModel> atualizar (@Parameter(description = "Id de um proprietário", example = "1", required = true)Long proprietarioId,
                                                        @RequestBody(description = "Representação de um proprietário com dados para atualizar", required = true)ProprietarioInputModel proprietarioInputModel);

    @Operation(summary = "Remove um proprietário", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Id de propreitário inválido", content = @Content(schema = @Schema))
    })
    public ResponseEntity<Void> remover(@Parameter(description = "Id de um proprietário", example = "1", required = true) Long proprietarioId);
}
