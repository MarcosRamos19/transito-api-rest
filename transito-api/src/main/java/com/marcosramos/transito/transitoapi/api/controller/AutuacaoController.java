package com.marcosramos.transito.transitoapi.api.controller;

import com.marcosramos.transito.transitoapi.api.assembler.AutuacaoAssembler;
import com.marcosramos.transito.transitoapi.api.controller.openapi.AutuacaoControllerOpenApi;
import com.marcosramos.transito.transitoapi.api.representationmodel.AutuacaoModel;
import com.marcosramos.transito.transitoapi.api.representationmodel.input.AutuacaoInputModel;
import com.marcosramos.transito.transitoapi.domain.model.Autuacao;
import com.marcosramos.transito.transitoapi.domain.model.Veiculo;
import com.marcosramos.transito.transitoapi.domain.service.CadastroAutuacaoService;
import com.marcosramos.transito.transitoapi.domain.service.CadastroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos/{veiculoId}/autuacoes")
public class AutuacaoController implements AutuacaoControllerOpenApi {

    private final CadastroAutuacaoService cadastroAutuacaoService;
    private final AutuacaoAssembler autuacaoAssembler;
    private final CadastroVeiculoService cadastroVeiculoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutuacaoModel registrar(@PathVariable Long veiculoId, @Valid @RequestBody AutuacaoInputModel autuacaoInputModel){

        Autuacao novaAutuacao = autuacaoAssembler.toEntity(autuacaoInputModel);
        Autuacao autuacaoCadastrada = cadastroAutuacaoService.cadastrar(veiculoId, novaAutuacao);

        return autuacaoAssembler.toModel(autuacaoCadastrada);
    }
    @GetMapping
    public List<AutuacaoModel> listar(@PathVariable Long veiculoId){
        Veiculo veiculo = cadastroVeiculoService.buscar(veiculoId);
        return autuacaoAssembler.toCollectionModel(veiculo.getAutuacoes());
    }
}
