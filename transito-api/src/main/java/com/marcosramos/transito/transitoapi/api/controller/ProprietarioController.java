package com.marcosramos.transito.transitoapi.api.controller;

import com.marcosramos.transito.transitoapi.api.assembler.ProprietarioAssembler;
import com.marcosramos.transito.transitoapi.api.controller.openapi.ProprietarioControllerOpenApi;
import com.marcosramos.transito.transitoapi.api.representationmodel.ProprietarioModel;
import com.marcosramos.transito.transitoapi.api.representationmodel.input.ProprietarioInputModel;
import com.marcosramos.transito.transitoapi.domain.model.Proprietario;
import com.marcosramos.transito.transitoapi.domain.repository.ProprietarioRepository;
import com.marcosramos.transito.transitoapi.domain.service.CadastroProprietarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/proprietarios")
@AllArgsConstructor
public class ProprietarioController implements ProprietarioControllerOpenApi {

    private final ProprietarioRepository proprietarioRepository;
    private final CadastroProprietarioService  cadastroProprietarioService;
    private final ProprietarioAssembler proprietarioAssembler;

    @GetMapping
    public List<ProprietarioModel> listar(){
        return proprietarioAssembler.toCollectionModel(proprietarioRepository.findAll()) ;
    }
    @GetMapping("/{proprietarioId}")
    public ResponseEntity<ProprietarioModel> buscar(@PathVariable Long proprietarioId){
     return proprietarioRepository.findById(proprietarioId)
             .map(proprietarioAssembler::toModel)
             .map(ResponseEntity::ok)
             .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProprietarioModel adicionar(@Valid @RequestBody ProprietarioInputModel proprietarioInputModel){
        Proprietario novoProprietario = proprietarioAssembler.toEntitiy(proprietarioInputModel);
        Proprietario proprietarioCadastrado = cadastroProprietarioService.salvar(novoProprietario);

       return proprietarioAssembler.toModel(proprietarioCadastrado);
    }
    @PutMapping("/{proprietarioId}")
    public ResponseEntity<ProprietarioModel> atualizar (@PathVariable Long proprietarioId, @Valid @RequestBody ProprietarioInputModel proprietarioInputModel){
        if (!proprietarioRepository.existsById(proprietarioId)){
            return ResponseEntity.notFound().build();
        }
        Proprietario proprietario = proprietarioAssembler.toEntitiy(proprietarioInputModel);
        proprietario.setId(proprietarioId);
        Proprietario proprietarioAtualizado = cadastroProprietarioService.salvar(proprietario);

        return ResponseEntity.ok(proprietarioAssembler.toModel(proprietarioAtualizado));
    }
    @DeleteMapping("/{proprietarioId}")
    public ResponseEntity<Void> remover(@PathVariable Long proprietarioId){
        if (!proprietarioRepository.existsById(proprietarioId)){
            return ResponseEntity.notFound().build();
        }
        cadastroProprietarioService.excluir(proprietarioId);
        return ResponseEntity.noContent().build();
    }

}
