package br.com.api.service;

import br.com.api.dto.PetDTO;
import br.com.api.dto.conversor.PetConversor;
import br.com.api.error.exception.*;
import br.com.api.repository.PetRepository;
import br.com.api.repository.entities.Cliente;
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
public class PetService implements ServiceContract<Pet, PetDTO> {

    private final Logger logger = LoggerFactory.getLogger(PetService.class);

    @Autowired
    private PetRepository repository;

    @Autowired
    private PetConversor conversor;

    @Autowired
    private ClienteService clienteService;

    @Override
    public List<PetDTO> listar() {

        Optional<List<Pet>> petsOptional;

        try {

            petsOptional = repository.findByFgAtivo(true);

        } catch (Exception ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ExceptionCustomizada(mensagem);
        }

        List<Pet> pets = petsOptional.orElseThrow(() -> new PetNaoEncontratoException());

        if (pets != null && pets.size() > 0) {
            return pets.stream().map(pet ->
                    conversor.converterParaDTO(pet)).collect(Collectors.toList());
        } else {
            throw new PetNaoEncontratoException();
        }
    }

    @Override
    public PetDTO buscarPorId(Long petId) {

        Optional<Pet> petOptional;

        ParametrosRequestValidador.validar(petId);

        try {

            petOptional = repository.findByIdAndFgAtivo(petId, true);

        } catch (Exception ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ExceptionCustomizada(mensagem);
        }

        final Pet pet = petOptional.orElseThrow(() -> new PetNaoEncontratoException(petId));
        return conversor.converterParaDTO(pet);
    }

    public List<PetDTO> buscarPorCliente(Long clienteId) {

        Optional<List<Pet>> petOptional;

        ParametrosRequestValidador.validar(clienteId);

        try {

            petOptional = repository.buscarPorClienteId(clienteId);

        } catch (Exception ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ExceptionCustomizada(mensagem);
        }

        final List<Pet> pets = petOptional.orElseThrow(() -> new ClienteSemPetException(clienteId));

        if (pets != null && pets.size() > 0) {
            return pets.stream().map(pet -> conversor.converterParaDTO(pet)).collect(Collectors.toList());
        } else {
            throw new ClienteSemPetException(clienteId);
        }
    }

    @Override
    public PetDTO salvar(PetDTO petDTO) {

        try {

            final Cliente cliente = clienteService.buscarEntidadePorId(petDTO.getClienteId());
            final Pet pet = repository.save(conversor.converterParaEntidade(petDTO, cliente));
            return conversor.converterParaDTO(pet);

        } catch (DataIntegrityViolationException ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ConstraintVioladaException();

        } catch (Exception ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ExceptionCustomizada(mensagem);
        }
    }

    @Override
    @Transactional
    public void excluir(Long petId) {

        ParametrosRequestValidador.validar(petId);

        try {

            final Optional<Pet> petOptional =
                    repository.findByIdAndFgAtivo(petId, true);

            final Pet pet = petOptional.orElseThrow(
                    () -> new PetNaoEncontratoException(petId));

            pet.setFgAtivo(false);

        } catch (Exception ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ExceptionCustomizada(mensagem);
        }
    }

    @Override
    public PetDTO atualizar(PetDTO petDTO) {

        final Long petId = petDTO.getPetId();

        ParametrosRequestValidador.validar(petId);

        try {

            final Pet pet = conversor.converterParaEntidadeAtualizacao(petDTO);
            final Pet petAtualizado = repository.save(pet);

            return conversor.converterParaDTO(petAtualizado);

        } catch (DataIntegrityViolationException ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ConstraintVioladaException();

        } catch (Exception ex) {

            final String mensagem = ExceptionUtils.getRootCauseMessage(ex);
            logger.error(mensagem, ex);

            throw new ExceptionCustomizada(mensagem);
        }
    }
}
