package br.com.api.util;

import br.com.api.dto.ClienteContatoDTO;
import br.com.api.dto.ClienteDTO;
import br.com.api.dto.ClienteEnderecoDTO;
import br.com.api.dto.PetDTO;

import java.util.Date;

public class DtoStore {

    public static ClienteDTO criarClienteDTO() {
        return ClienteDTO.builder()
                .senha("123mudar")
                .email("apii@teste.com")
                .cpf("146.058.230-69")
                .dataNascimento(new Date())
                .nome("Teste Apii")
                .sexo("MASCULINO")
                .build();
    }

    public static PetDTO criarPetDTO() {
        return PetDTO.builder()
                .tipo("CACHORRO")
                .sexo("FEMEA")
                .raca("SRD")
                .porte("MEDIO")
                .peso(20.00)
                .nome("TESTE")
                .cor("COR")
                .dataNascimento(new Date())
                .build();
    }

    public static ClienteContatoDTO criarContatoDTO() {
        return ClienteContatoDTO.builder()
                .tipo("CELULAR")
                .numero(912345678)
                .ddd(11)
                .build();
    }

    public static ClienteEnderecoDTO criarEnderecoDTO() {
        return ClienteEnderecoDTO.builder()
                .tipo("RESIDENCIAL")
                .numero(1)
                .logradouro("AVENIDA Teste")
                .estado("SP")
                .complemento("AP 1")
                .cidade("SÃO PAULO")
                .cep("12345-000")
                .bairro("JD TESTE")
                .build();
    }
}
