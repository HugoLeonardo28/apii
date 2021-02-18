package br.com.api.dto;

import br.com.api.dto.validacao.GruposValidacao;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetDTO {

    @NotNull(groups = GruposValidacao.PetEdicao.class)
    @Positive(groups = GruposValidacao.PetEdicao.class)
    private Long petId;

    @NotNull
    @Positive(groups = GruposValidacao.PetCadastro.class)
    private Double peso;

    @NotNull(groups = GruposValidacao.PetCadastro.class)
    private Date dataNascimento;

    @NotBlank(groups = GruposValidacao.PetCadastro.class)
    private String nome;

    @NotBlank(groups = GruposValidacao.PetCadastro.class)
    private String raca;

    @NotBlank(groups = GruposValidacao.PetCadastro.class)
    private String cor;

    @NotBlank(groups = GruposValidacao.PetCadastro.class)
    private String sexo;

    @NotBlank(groups = GruposValidacao.PetCadastro.class)
    private String tipo;

    @NotBlank(groups = GruposValidacao.PetCadastro.class)
    private String porte;

    @NotNull
    @Positive(groups = GruposValidacao.PetCadastro.class)
    private Long clienteId;

}
