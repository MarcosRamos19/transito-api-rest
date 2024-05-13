package com.marcosramos.transito.transitoapi.domain.service;

import com.marcosramos.transito.transitoapi.domain.exception.DomainException;
import com.marcosramos.transito.transitoapi.domain.model.Proprietario;
import com.marcosramos.transito.transitoapi.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CadastroProprietarioService {

    private final ProprietarioRepository proprietarioRepository;

    public Proprietario buscar(Long proprietarioId){
        return proprietarioRepository.findById(proprietarioId)
                .orElseThrow(()-> new DomainException("Proprietário não encontrado"));

    }

    @Transactional
    public Proprietario salvar(Proprietario proprietario){
        boolean emailEmUso = proprietarioRepository.findByEmail(proprietario.getEmail())
                .filter(p -> !p.equals(proprietario) )
                .isPresent();


        if (emailEmUso){
            throw new DomainException("Já existe um proprietário cadastrado com esse email.");
        }

        return proprietarioRepository.save(proprietario);
    }
    @Transactional
    public void excluir(Long proprietarioId){
        proprietarioRepository.deleteById(proprietarioId);
    }


}
