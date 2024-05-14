package com.marcosramos.transito.transitoapi.domain.service;

import com.marcosramos.transito.transitoapi.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ApreensaoVeiculoService {

    private CadastroVeiculoService cadastroVeiculoService;

    @Transactional
    public void apreender(Long veiculoId){
        Veiculo veiculo = cadastroVeiculoService.buscar(veiculoId);
        veiculo.apreender();
    }
    @Transactional
    public void absolver(Long veiculoId){
        Veiculo veiculo = cadastroVeiculoService.buscar(veiculoId);
        veiculo.removerApreensao();
    }
}
