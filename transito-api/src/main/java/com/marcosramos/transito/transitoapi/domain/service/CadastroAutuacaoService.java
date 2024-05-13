package com.marcosramos.transito.transitoapi.domain.service;

import com.marcosramos.transito.transitoapi.domain.model.Autuacao;
import com.marcosramos.transito.transitoapi.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CadastroAutuacaoService {

    private CadastroVeiculoService cadastroVeiculoService;

    @Transactional
    public Autuacao cadastrar(Long veiculoId, Autuacao novaAutuacao){
        Veiculo veiculo = cadastroVeiculoService.buscar(veiculoId);

        return veiculo.adicionarAutuacao(novaAutuacao);
    }
}
