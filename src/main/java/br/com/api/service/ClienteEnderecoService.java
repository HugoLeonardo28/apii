package br.com.api.service;

import br.com.api.dto.ClienteDTO;
import br.com.api.dto.ClienteEnderecoDTO;
import br.com.api.dto.conversor.ClienteEnderecoConversor;
import br.com.api.error.exception.*;
import br.com.api.repository.ClienteEnderecoRepository;
import br.com.api.repository.ClienteRepository;
import br.com.api.repository.entities.Cliente;
import br.com.api.repository.entities.ClienteEndereco;
import br.com.api.repository.entities.Pet;
import br.com.api.service.validacao.ParametrosRequestValidador;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteEnderecoService implements ServiceContract<ClienteEndereco, ClienteEnderecoDTO> {

    private final Logger logger = LoggerFactory.getLogger(ClienteEnderecoService.class);

    @Autowired
    private ClienteEnderecoRepository repository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteEnderecoConversor conversor;

    @Override
    public List<ClienteEnderecoDTO> listar() {

        Optional<List<ClienteEndereco>> enderecosOptional;

        try {

            enderecosOptional = repository.findByFgAtivo(true);

        } catch (Exception ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ExceptionCustomizada();
        }

        final List<ClienteEndereco> enderecos =
                enderecosOptional.orElseThrow(() -> new EnderecoNaoEncontratoException());

        if (enderecos != null && enderecos.size() > 0) {
            return enderecos.stream().map(endereco ->
                    conversor.converterParaDTO(endereco)).collect(Collectors.toList());
        } else {
            throw new EnderecoNaoEncontratoException();
        }
    }

    @Override
    public ClienteEnderecoDTO buscarPorId(final Long enderecoId) {

        Optional<ClienteEndereco> clienteEnderecoOptional;

        ParametrosRequestValidador.validar(enderecoId);

        try {

            clienteEnderecoOptional = repository.findByIdAndFgAtivo(enderecoId, true);

        } catch (Exception ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ExceptionCustomizada();
        }

        final ClienteEndereco clienteEndereco = clienteEnderecoOptional.orElseThrow(() -> new EnderecoNaoEncontratoException(enderecoId));
        return conversor.converterParaDTO(clienteEndereco);
    }

    public List<ClienteEnderecoDTO> buscarPorClienteId(final Long clienteId) {

        Optional<List<ClienteEndereco>> clienteEnderecoOptional;

        ParametrosRequestValidador.validar(clienteId);

        try {

            clienteEnderecoOptional = repository.listarPorClienteId(clienteId);

        } catch (Exception ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ExceptionCustomizada();
        }

        final List<ClienteEndereco> clienteEndereco = clienteEnderecoOptional.orElseThrow(() -> new ClienteSemEnderecoException(clienteId));

        if (clienteEndereco != null && clienteEndereco.size() > 0) {
            return clienteEndereco.stream().map(endereco -> conversor.converterParaDTO(endereco)).collect(Collectors.toList());
        } else {
            throw new ClienteSemEnderecoException(clienteId);
        }
    }

    @Override
    public ClienteEnderecoDTO salvar(final ClienteEnderecoDTO enderecoDTO) {

        try {

            final Cliente cliente = clienteService.buscarEntidadePorId(enderecoDTO.getClienteId());
            final ClienteEndereco clienteEndereco = conversor.converterParaEntidade(enderecoDTO, cliente);

            return conversor.converterParaDTO(repository.save(clienteEndereco));

        } catch (DataIntegrityViolationException ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ConstraintVioladaException();

        } catch (Exception ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ExceptionCustomizada();
        }
    }

    @Override
    @Transactional
    public void excluir(Long enderecoId) {

        ParametrosRequestValidador.validar(enderecoId);

        try {

            final Optional<ClienteEndereco> enderecoOptional =
                    repository.findByIdAndFgAtivo(enderecoId, true);

            final ClienteEndereco endereco = enderecoOptional.orElseThrow(
                    () -> new EnderecoNaoEncontratoException(enderecoId));

            endereco.setFgAtivo(false);

        } catch (Exception ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ExceptionCustomizada();
        }
    }

    @Override
    public ClienteEnderecoDTO atualizar(ClienteEnderecoDTO enderecoDTO) {

        final Long enderecoId = enderecoDTO.getEnderecoId();

        ParametrosRequestValidador.validar(enderecoId);

        try {

            final ClienteEndereco clienteEndereco = conversor.converterParaEntidadeAtualizacao(enderecoDTO);
            final ClienteEndereco clienteEnderecoAtualizado = repository.save(clienteEndereco);

            return conversor.converterParaDTO(clienteEnderecoAtualizado);

        } catch (DataIntegrityViolationException ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ConstraintVioladaException();

        } catch (Exception ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ExceptionCustomizada();
        }
    }

}
