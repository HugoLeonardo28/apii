package br.com.api.dto;

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
public class ClienteContatoDTO {

    @NotNull(groups = GruposValidacao.ClienteContatoEdicao.class)
    @Positive(groups = GruposValidacao.ClienteContatoEdicao.class)
    private Long contatoId;

    @NotNull(groups = GruposValidacao.ClienteContatoCadastro.class)
    private String tipo;

    @NotNull(groups = GruposValidacao.ClienteContatoCadastro.class)
    @Positive(groups = GruposValidacao.ClienteContatoCadastro.class)
    private Integer ddd;

    @NotNull(groups = GruposValidacao.ClienteContatoCadastro.class)
    @Positive(groups = GruposValidacao.ClienteContatoCadastro.class)
    private Integer numero;

    @Positive(groups = GruposValidacao.ClienteContatoCadastro.class)
    private Long clienteId;

}
