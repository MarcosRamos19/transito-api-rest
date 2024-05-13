package com.marcosramos.transito.transitoapi.api.assembler;

import com.marcosramos.transito.transitoapi.api.representationmodel.VeiculoModel;
import com.marcosramos.transito.transitoapi.api.representationmodel.input.VeiculoInputModel;
import com.marcosramos.transito.transitoapi.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class VeiculoAssembler {

    private final ModelMapper modelMapper;

    public Veiculo toEntitiy(VeiculoInputModel veiculoInputModel){
        return modelMapper.map(veiculoInputModel, Veiculo.class);
    }

    public VeiculoModel toModel(Veiculo veiculo){
        return modelMapper.map(veiculo, VeiculoModel.class);
    }
    public List<VeiculoModel> toCollectionModel(List<Veiculo> veiculos){

        return veiculos.stream()
                .map(this::toModel)
                .toList();
    }
}
