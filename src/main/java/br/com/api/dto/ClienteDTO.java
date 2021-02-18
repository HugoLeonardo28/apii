package br.com.api.dto;

import br.com.api.dto.validacao.GruposValidacao;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    @NotNull(groups = GruposValidacao.ClienteEdicao.class)
    @Positive(groups = GruposValidacao.ClienteEdicao.class)
    private Long clienteId;

    @NotNull(groups = GruposValidacao.ClienteCadastro.class)
    private Date dataNascimento;

    @NotNull(groups = GruposValidacao.ClienteCadastro.class)
    @Size(min = 1, groups = GruposValidacao.ClienteCadastro.class)
    private List<ClienteEnderecoDTO> enderecos;

    @NotNull(groups = GruposValidacao.ClienteCadastro.class)
    @Size(min = 1, groups = GruposValidacao.ClienteCadastro.class)
    private List<ClienteContatoDTO> contatos;

    @NotNull(groups = GruposValidacao.ClienteCadastro.class)
    @Size(min = 1, groups = GruposValidacao.ClienteCadastro.class)
    private List<PetDTO> pets;

    @CPF(groups = GruposValidacao.ClienteCadastro.class)
    @NotNull(groups = GruposValidacao.ClienteCadastro.class)
    private String cpf;

    @NotNull(groups = GruposValidacao.ClienteCadastro.class)
    private String nome;

    @NotNull(groups = GruposValidacao.ClienteCadastro.class)
    private String sexo;

    @JsonInclude(content = JsonInclude.Include.NON_NULL)
    @NotNull(groups = GruposValidacao.ClienteCadastro.class)
    @Email(groups = GruposValidacao.ClienteCadastro.class)
    private String email;

    @NotNull(groups = GruposValidacao.ClienteCadastro.class)
    private String senha;

    public void adicionarPet(PetDTO petDTO) {

        if(pets == null) {
            this.pets = new ArrayList<>();
        }

        this.pets.add(petDTO);
    }

    public void adicionarContato(ClienteContatoDTO contatoDTO) {

        if(contatos == null) {
            this.contatos = new ArrayList<>();
        }

        this.contatos.add(contatoDTO);
    }

    public void adicionarEndereco(ClienteEnderecoDTO enderecoDTO) {

        if(enderecos == null) {
            this.enderecos = new ArrayList<>();
        }

        this.enderecos.add(enderecoDTO);
    }

}
