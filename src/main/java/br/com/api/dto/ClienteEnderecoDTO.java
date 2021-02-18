package br.com.api.dto;

import br.com.api.dto.validacao.Cep;
import br.com.api.dto.validacao.GruposValidacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEnderecoDTO {

    @NotNull(groups = GruposValidacao.ClienteEnderecoEdicao.class)
    @Positive(groups = GruposValidacao.ClienteEnderecoEdicao.class)
    private Long enderecoId;

    @NotNull(groups = GruposValidacao.ClienteEnderecoCadastro.class)
    @Positive(groups = GruposValidacao.ClienteEnderecoCadastro.class)
    private Integer numero;

    @Cep(groups = GruposValidacao.ClienteContatoCadastro.class)
    @NotNull(groups = GruposValidacao.ClienteEnderecoCadastro.class)
    private String cep;

    @NotNull(groups = GruposValidacao.ClienteEnderecoCadastro.class)
    private String tipo;

    @NotNull(groups = GruposValidacao.ClienteEnderecoCadastro.class)
    private String estado;

    @NotNull(groups = GruposValidacao.ClienteEnderecoCadastro.class)
    private String cidade;

    @NotNull(groups = GruposValidacao.ClienteEnderecoCadastro.class)
    private String bairro;

    @NotNull(groups = GruposValidacao.ClienteEnderecoCadastro.class)
    private String logradouro;

    @NotNull(groups = GruposValidacao.ClienteEnderecoCadastro.class)
    private String complemento;

    @NotNull(groups = GruposValidacao.ClienteEnderecoCadastro.class)
    private Long clienteId;

}
