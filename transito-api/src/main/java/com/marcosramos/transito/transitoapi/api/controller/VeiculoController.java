package com.marcosramos.transito.transitoapi.api.controller;

import com.marcosramos.transito.transitoapi.api.assembler.VeiculoAssembler;
import com.marcosramos.transito.transitoapi.api.controller.openapi.VeiculoControllerOpenApi;
import com.marcosramos.transito.transitoapi.api.representationmodel.VeiculoModel;
import com.marcosramos.transito.transitoapi.api.representationmodel.input.VeiculoInputModel;
import com.marcosramos.transito.transitoapi.domain.model.Veiculo;
import com.marcosramos.transito.transitoapi.domain.repository.VeiculoRepository;
import com.marcosramos.transito.transitoapi.domain.service.CadastroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/veiculos")
@AllArgsConstructor
public class VeiculoController implements VeiculoControllerOpenApi {

    private final VeiculoRepository veiculoRepository;
    private final CadastroVeiculoService cadastroVeiculoService;
    private final VeiculoAssembler veiculoAssembler;

    @GetMapping
    public List<VeiculoModel> listar(){
        return veiculoAssembler.toCollectionModel(veiculoRepository.findAll());
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<VeiculoModel> buscar(@PathVariable Long veiculoId){
        return  veiculoRepository.findById(veiculoId)
                .map(veiculoAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoModel cadastrar(@Valid @RequestBody VeiculoInputModel veiculoInput){
        Veiculo novoVeiculo = veiculoAssembler.toEntitiy(veiculoInput);
        Veiculo veiculoCadastrado = cadastroVeiculoService.cadastrar(novoVeiculo);

        return veiculoAssembler.toModel(veiculoCadastrado);

    }

}
