package com.marcosramos.transito.transitoapi.domain.service;

import com.marcosramos.transito.transitoapi.domain.exception.DomainException;
import com.marcosramos.transito.transitoapi.domain.exception.EntidadeNaoEncontradaException;
import com.marcosramos.transito.transitoapi.domain.model.Proprietario;
import com.marcosramos.transito.transitoapi.domain.model.StatusVeiculo;
import com.marcosramos.transito.transitoapi.domain.model.Veiculo;
import com.marcosramos.transito.transitoapi.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class CadastroVeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final CadastroProprietarioService cadastroProprietarioService;

    public Veiculo buscar(Long veiculoId){
        return veiculoRepository.findById(veiculoId)
                .orElseThrow(()-> new EntidadeNaoEncontradaException("Veículo não encontrado"));
    }
    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo){
        if (novoVeiculo.getId() != null){
          throw new DomainException("Veículio a ser cadastrado não deve possuir um ID");
        }
        boolean placaEmUso = veiculoRepository.findByPlaca(novoVeiculo.getPlaca())
                .filter(veiculo -> !veiculo.equals(novoVeiculo))
                .isPresent();
        if (placaEmUso){
            throw new DomainException("Já existe um veículo cadastrado com essa placa.");
        }

        Proprietario proprietario = cadastroProprietarioService.buscar(novoVeiculo.getProprietario().getId());

        novoVeiculo.setProprietario(proprietario);
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(OffsetDateTime.now());
        return veiculoRepository.save(novoVeiculo);
    }
}
