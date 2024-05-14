package com.marcosramos.transito.transitoapi.domain.model;

import com.marcosramos.transito.transitoapi.domain.exception.DomainException;
import com.marcosramos.transito.transitoapi.domain.validation.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.ProprietarioId.class)
    @NotNull
    @ManyToOne
    //  @JoinColumn(name = "proprietario_id")
    private Proprietario proprietario;

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @NotBlank
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}") //validação formato de placas antigas e novas
    private String placa;

    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;

    private OffsetDateTime dataCadastro;
    private  OffsetDateTime dataApreensao;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
    private List<Autuacao> autuacoes = new ArrayList<>();

    public Autuacao adicionarAutuacao(Autuacao autuacao){
        autuacao.setDataOcorrencia(OffsetDateTime.now());
        autuacao.setVeiculo(this);
        getAutuacoes().add(autuacao);
        return autuacao;
    }

    public void apreender(){
        if (estaApreendido()){
            throw new DomainException("Este veiculo já está apreendido");
        }
        setStatus(StatusVeiculo.APREENDIDO);
        setDataApreensao(OffsetDateTime.now());
    }
    public void removerApreensao(){
        if (naoEstaApreendido()){
            throw new DomainException("Veículo não está apreendido");
        }
        setStatus(StatusVeiculo.REGULAR);
        setDataApreensao(null);
    }
    public boolean estaApreendido(){
        return StatusVeiculo.APREENDIDO.equals(this.getStatus());
    }
    public boolean naoEstaApreendido(){
        return !estaApreendido();
    }
}
