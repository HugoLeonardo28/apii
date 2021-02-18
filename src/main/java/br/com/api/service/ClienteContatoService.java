package br.com.api.service;

import br.com.api.dto.ClienteContatoDTO;
import br.com.api.dto.conversor.ClienteContatoConversor;
import br.com.api.error.exception.*;
import br.com.api.repository.ClienteContatoRepository;
import br.com.api.repository.entities.Cliente;
import br.com.api.repository.entities.ClienteContato;
import br.com.api.repository.entities.ClienteEndereco;
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
public class ClienteContatoService implements ServiceContract<ClienteContato, ClienteContatoDTO> {

    private final Logger logger = LoggerFactory.getLogger(ClienteContatoService.class);

    @Autowired
    private ClienteContatoRepository repository;

    @Autowired
    private ClienteContatoConversor conversor;

    @Autowired
    private ClienteService clienteService;

    @Override
    public List<ClienteContatoDTO> listar() {

        Optional<List<ClienteContato>> contatosOptional;

        try {
            contatosOptional = repository.findByFgAtivo(true);
        } catch (Exception ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ExceptionCustomizada();
        }

        final List<ClienteContato> contatos =
                contatosOptional.orElseThrow(() -> new ContatoNaoEncontratoException());

        if (contatos != null && contatos.size() > 0) {

            logger.info("Encontrados %s contatos", contatos.size());

            return contatos.stream().map(contato ->
                    conversor.converterParaDTO(contato)).collect(Collectors.toList());
        } else {
            throw new ContatoNaoEncontratoException();
        }
    }

    @Override
    public ClienteContatoDTO buscarPorId(final Long contatoId) {

        Optional<ClienteContato> contatoOptional;

        ParametrosRequestValidador.validar(contatoId);

        try {

            contatoOptional = repository.findByIdAndFgAtivo(contatoId, true);

        } catch (Exception ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ExceptionCustomizada();
        }

        final ClienteContato clienteContato = contatoOptional.orElseThrow(() -> new ContatoNaoEncontratoException(contatoId));
        return conversor.converterParaDTO(clienteContato);
    }

    public List<ClienteContatoDTO> listarPorClienteId(final Long clienteId) {

        Optional<List<ClienteContato>> contatoOptional;

        ParametrosRequestValidador.validar(clienteId);

        try {

            contatoOptional = repository.listarPorClienteId(clienteId);

        } catch (Exception ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ExceptionCustomizada();
        }

        final List<ClienteContato> contatos = contatoOptional.orElseThrow(() -> new ClienteSemContatoException(clienteId));

        if (contatos != null && contatos.size() > 0) {

            logger.info("Encontrados %s contatos", contatos.size());

            return contatos.stream().map(contato -> conversor.converterParaDTO(contato)).collect(Collectors.toList());

        } else {
            throw new ClienteSemContatoException(clienteId);
        }
    }

    @Override
    public ClienteContatoDTO salvar(final ClienteContatoDTO contatoDTO) {

        try {

            final Cliente cliente = clienteService.buscarEntidadePorId(contatoDTO.getClienteId());
            final ClienteContato clienteContato = conversor.converterParaEntidade(contatoDTO, cliente);

            return conversor.converterParaDTO(repository.save(clienteContato));

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
    public void excluir(Long contatoId) {

        ParametrosRequestValidador.validar(contatoId);

        try {

            final Optional<ClienteContato> contatoOptional =
                    repository.findByIdAndFgAtivo(contatoId, true);

            final ClienteContato contato = contatoOptional.orElseThrow(
                    () -> new ContatoNaoEncontratoException(contatoId));

            contato.setFgAtivo(false);

        } catch (Exception ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ExceptionCustomizada();
        }
    }

    @Override
    public ClienteContatoDTO atualizar(ClienteContatoDTO contatoDTO) {

        final Long contatoId = contatoDTO.getContatoId();

        ParametrosRequestValidador.validar(contatoId);

        try {

            final ClienteContato clienteContato = conversor.converterParaEntidadeAtualizacao(contatoDTO);
            final ClienteContato clienteContatoAtualizado = repository.save(clienteContato);

            return conversor.converterParaDTO(clienteContatoAtualizado);

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
