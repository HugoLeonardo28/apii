package br.com.api.dto.conversor;

import br.com.api.dto.ClienteContatoDTO;
import br.com.api.dto.ClienteDTO;
import br.com.api.dto.ClienteEnderecoDTO;
import br.com.api.dto.PetDTO;
import br.com.api.repository.ClienteRepository;
import br.com.api.repository.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class ClienteConversor implements Conversor<ClienteDTO, Cliente> {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClienteEnderecoConversor enderecoConversor;

    @Autowired
    private ClienteContatoConversor contatoConversor;

    @Autowired
    private PetConversor petConversor;

    @Override
    public ClienteDTO converterParaDTO(Cliente entidade) {

        String cpf = entidade.getCpf().split(Pattern.quote("."))[2];

        final List<ClienteContatoDTO> contatos = entidade.getContatos().stream().map(
                contato -> contatoConversor.converterParaDTO(contato)
        ).collect(Collectors.toList());

        final List<PetDTO> pets = entidade.getPets().stream().map(
                pet -> petConversor.converterParaDTO(pet)
        ).collect(Collectors.toList());

        final List<ClienteEnderecoDTO> enderecos = entidade.getEnderecos().stream().map(
                endereco -> enderecoConversor.converterParaDTO(endereco)
        ).collect(Collectors.toList());

        return ClienteDTO.builder()
                .clienteId(entidade.getId())
                .sexo(entidade.getSexo())
                .nome(entidade.getNome())
                .email(entidade.getUsuario().getEmail())
                .senha("******")
                .enderecos(enderecos)
                .dataNascimento(entidade.getDataNascimento())
                .contatos(contatos)
                .pets(pets)
                .cpf("***.***.".concat(cpf))
                .build();
    }

    @Override
    public Cliente converterParaEntidade(ClienteDTO dto) {

        final Cliente cliente = new Cliente();

        final Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setFgAtivo(true);
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));

        final List<ClienteContato> contatoList = dto.getContatos().stream().map(
                contatoDTO -> {
                    final ClienteContato clienteContato = contatoConversor.converterParaEntidade(contatoDTO);
                    clienteContato.setCliente(cliente);
                    return clienteContato;
                }
        ).collect(Collectors.toList());

        final List<Pet> petList = dto.getPets().stream().map(
                petDTO -> petConversor.converterParaEntidade(petDTO)
        ).collect(Collectors.toList());

        final List<ClienteEndereco> enderecoList = dto.getEnderecos().stream().map(
                enderecoDTO -> {
                    final ClienteEndereco clienteEndereco = enderecoConversor.converterParaEntidade(enderecoDTO);
                    clienteEndereco.setCliente(cliente);
                    return clienteEndereco;
                }
        ).collect(Collectors.toList());

        cliente.setCpf(dto.getCpf());
        cliente.setEnderecos(enderecoList);
        cliente.setPets(petList);
        cliente.setUsuario(usuario);
        cliente.setSexo(dto.getSexo());
        cliente.setNome(dto.getNome());
        cliente.setFgAtivo(true);
        cliente.setDataNascimento(dto.getDataNascimento());
        cliente.setContatos(contatoList);

        return cliente;
    }

    public Cliente converterParaEntidadeAtualizacao(ClienteDTO clienteDTO) {

        final Long clienteId = clienteDTO.getClienteId();

        final Cliente cliente = repository.getOne(clienteId);

        cliente.setNome(clienteDTO.getNome());
        cliente.setSexo(clienteDTO.getSexo());
        cliente.setDataNascimento(clienteDTO.getDataNascimento());

        return cliente;
    }
}
