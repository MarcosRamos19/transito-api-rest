package com.marcosramos.transito.transitoapi.api.assembler;

import com.marcosramos.transito.transitoapi.api.representationmodel.AutuacaoModel;
import com.marcosramos.transito.transitoapi.api.representationmodel.input.AutuacaoInputModel;
import com.marcosramos.transito.transitoapi.domain.model.Autuacao;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class AutuacaoAssembler {

    private final ModelMapper modelMapper;

    public Autuacao toEntity(AutuacaoInputModel autuacaoInputModel){
        return modelMapper.map(autuacaoInputModel, Autuacao.class);
    }

    public AutuacaoModel toModel(Autuacao autuacao){
        return modelMapper.map(autuacao, AutuacaoModel.class);
    }
    public List<AutuacaoModel> toCollectionModel(List<Autuacao> autuacoes){
        return autuacoes.stream()
                .map(this::toModel)
                .toList();
    }
}
