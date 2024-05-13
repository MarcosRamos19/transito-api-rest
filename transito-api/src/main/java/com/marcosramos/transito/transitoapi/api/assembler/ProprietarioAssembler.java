package com.marcosramos.transito.transitoapi.api.assembler;

import com.marcosramos.transito.transitoapi.api.representationmodel.ProprietarioModel;
import com.marcosramos.transito.transitoapi.api.representationmodel.input.ProprietarioInputModel;
import com.marcosramos.transito.transitoapi.domain.model.Proprietario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ProprietarioAssembler {

    private final ModelMapper modelMapper;

    public Proprietario toEntitiy(ProprietarioInputModel proprietarioInputModel){
        return modelMapper.map(proprietarioInputModel, Proprietario.class);
    }

    public ProprietarioModel toModel(Proprietario proprietario){
        return modelMapper.map(proprietario, ProprietarioModel.class);
    }
    public List<ProprietarioModel> toCollectionModel(List<Proprietario> proprietarios){

        return proprietarios.stream()
                .map(this::toModel)
                .toList();
    }
}
