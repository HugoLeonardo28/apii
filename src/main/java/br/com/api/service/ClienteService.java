package br.com.api.service;

import br.com.api.dto.ClienteDTO;
import br.com.api.dto.conversor.ClienteConversor;
import br.com.api.error.exception.ClienteNaoEncontratoException;
import br.com.api.error.exception.ConstraintVioladaException;
import br.com.api.error.exception.ExceptionCustomizada;
import br.com.api.repository.ClienteRepository;
import br.com.api.repository.entities.Cliente;
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
public class ClienteService implements ServiceContract<Cliente, ClienteDTO> {

    private final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ClienteConversor conversor;

    @Override
    public List<ClienteDTO> listar() {

        Optional<List<Cliente>> clientesOptional;

        try {

            clientesOptional = repository.findByFgAtivo(true);

        } catch (Exception ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ExceptionCustomizada();
        }

        final List<Cliente> clientes =
                clientesOptional.orElseThrow(() -> new ClienteNaoEncontratoException());

        if (clientes != null && clientes.size() > 0) {
            return clientes.stream().map(cliente ->
                    conversor.converterParaDTO(cliente)).collect(Collectors.toList());
        } else {
            throw new ClienteNaoEncontratoException();
        }
    }

    @Override
    public ClienteDTO buscarPorId(Long clienteId) {

        Optional<Cliente> clienteOptional;

        ParametrosRequestValidador.validar(clienteId);

        try {

            clienteOptional = repository.findByIdAndFgAtivo(clienteId, true);

        } catch (Exception ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ExceptionCustomizada();
        }

        final Cliente cliente = clienteOptional.orElseThrow(() -> new ClienteNaoEncontratoException(clienteId));
        return conversor.converterParaDTO(cliente);
    }

    @Override
    public ClienteDTO salvar(ClienteDTO clienteDTO) {

        try {

            final Cliente cliente = repository.save(conversor.converterParaEntidade(clienteDTO));
            return conversor.converterParaDTO(cliente);

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
    public void excluir(Long clienteId) {

        ParametrosRequestValidador.validar(clienteId);

        try {

            final Optional<Cliente> clienteOptional =
                    repository.findByIdAndFgAtivo(clienteId, true);

            final Cliente cliente = clienteOptional.orElseThrow(
                    () -> new ClienteNaoEncontratoException(clienteId));

            cliente.setFgAtivo(false);
            cliente.getUsuario().setFgAtivo(false);

        } catch (Exception ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ExceptionCustomizada();
        }
    }

    @Override
    public ClienteDTO atualizar(ClienteDTO clienteDTO) {

        final Long clienteId = clienteDTO.getClienteId();

        ParametrosRequestValidador.validar(clienteId);

        try {

            final Cliente cliente = conversor.converterParaEntidadeAtualizacao(clienteDTO);
            final Cliente clienteAtualizado = repository.save(cliente);

            return conversor.converterParaDTO(clienteAtualizado);

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

    public Cliente buscarEntidadePorId(Long clienteId) {

        Optional<Cliente> clienteOptional;

        ParametrosRequestValidador.validar(clienteId);

        try {

            clienteOptional = repository.findByIdAndFgAtivo(clienteId, true);

        } catch (Exception ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ExceptionCustomizada();
        }

        return clienteOptional.orElseThrow(() -> new ClienteNaoEncontratoException(clienteId));
    }
}
