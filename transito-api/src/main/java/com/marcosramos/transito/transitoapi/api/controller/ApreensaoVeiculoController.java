package com.marcosramos.transito.transitoapi.api.controller;

import com.marcosramos.transito.transitoapi.api.controller.openapi.ApreensaoVeiculoControllerOpenApi;
import com.marcosramos.transito.transitoapi.domain.service.ApreensaoVeiculoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/veiculos")
@AllArgsConstructor
public class ApreensaoVeiculoController  implements ApreensaoVeiculoControllerOpenApi {

    private final ApreensaoVeiculoService apreensaoVeiculoService;
    @PutMapping("/{veiculoId}/apreensao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apreender(@PathVariable Long veiculoId){
        apreensaoVeiculoService.apreender(veiculoId);
    }

    @DeleteMapping("/{veiculoId}/apreensao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerApreensao(@PathVariable Long veiculoId){
        apreensaoVeiculoService.absolver(veiculoId);
    }
}
