package br.com.api.dto.conversor;

import br.com.api.dto.ClienteDTO;
import br.com.api.dto.ClienteEnderecoDTO;
import br.com.api.repository.ClienteEnderecoRepository;
import br.com.api.repository.entities.Cliente;
import br.com.api.repository.entities.ClienteEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ClienteEnderecoConversor implements Conversor<ClienteEnderecoDTO, ClienteEndereco> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClienteEnderecoRepository repository;

    @Override
    public ClienteEnderecoDTO converterParaDTO(ClienteEndereco entidade) {

        return ClienteEnderecoDTO.builder()
                .bairro(entidade.getBairro())
                .cep(entidade.getCep())
                .cidade(entidade.getCidade())
                .complemento(entidade.getComplemento())
                .enderecoId(entidade.getId())
                .clienteId(entidade.getCliente().getId())
                .estado(entidade.getEstado())
                .logradouro(entidade.getLogradouro())
                .numero(entidade.getNumero())
                .tipo(entidade.getTipo())
                .build();
    }

    @Override
    public ClienteEndereco converterParaEntidade(ClienteEnderecoDTO dto) {

        return ClienteEndereco.builder()
                .bairro(dto.getBairro())
                .cep(dto.getCep())
                .cidade(dto.getCidade())
                .complemento(dto.getComplemento())
                .estado(dto.getEstado())
                .id(dto.getEnderecoId())
                .logradouro(dto.getLogradouro())
                .numero(dto.getNumero())
                .fgAtivo(true)
                .tipo(dto.getTipo())
                .build();
    }

    public ClienteEndereco converterParaEntidade(ClienteEnderecoDTO dto, Cliente cliente) {
        final ClienteEndereco clienteEndereco = this.converterParaEntidade(dto);
        clienteEndereco.setCliente(cliente);
        return clienteEndereco;
    }

    public ClienteEndereco converterParaEntidadeAtualizacao(ClienteEnderecoDTO enderecoDTO) {

        final Long enderecoId = enderecoDTO.getEnderecoId();

        final ClienteEndereco endereco = repository.getOne(enderecoId);

        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setTipo(enderecoDTO.getTipo());

        return endereco;
    }
}
