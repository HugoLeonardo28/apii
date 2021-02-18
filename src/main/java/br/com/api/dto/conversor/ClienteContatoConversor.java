package br.com.api.dto.conversor;

import br.com.api.dto.ClienteContatoDTO;
import br.com.api.dto.ClienteDTO;
import br.com.api.dto.ClienteEnderecoDTO;
import br.com.api.repository.ClienteContatoRepository;
import br.com.api.repository.entities.Cliente;
import br.com.api.repository.entities.ClienteContato;
import br.com.api.repository.entities.ClienteEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ClienteContatoConversor implements Conversor<ClienteContatoDTO, ClienteContato> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClienteContatoRepository repository;

    @Override
    public ClienteContatoDTO converterParaDTO(ClienteContato entidade) {
        return ClienteContatoDTO.builder()
                .contatoId(entidade.getId())
                .ddd(entidade.getDdd())
                .numero(entidade.getNumero())
                .clienteId(entidade.getCliente().getId())
                .tipo(entidade.getTipo())
                .build();
    }

    @Override
    public ClienteContato converterParaEntidade(ClienteContatoDTO dto) {

        return ClienteContato.builder()
                .ddd(dto.getDdd())
                .fgAtivo(true)
                .numero(dto.getNumero())
                .tipo(dto.getTipo())
                .build();
    }

    public ClienteContato converterParaEntidade(ClienteContatoDTO dto, Cliente cliente) {
        final ClienteContato clienteContato = this.converterParaEntidade(dto);
        clienteContato.setCliente(cliente);
        return clienteContato;
    }

    public ClienteContato converterParaEntidadeAtualizacao(ClienteContatoDTO contatoDTO) {

        final Long enderecoId = contatoDTO.getContatoId();

        final ClienteContato contato = repository.getOne(enderecoId);
        contato.setDdd(contatoDTO.getDdd());
        contato.setNumero(contatoDTO.getNumero());
        contato.setTipo(contatoDTO.getTipo());

        return contato;
    }
}
