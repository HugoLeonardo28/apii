package br.com.api.dto.conversor;

import br.com.api.dto.ClienteDTO;
import br.com.api.dto.PetDTO;
import br.com.api.repository.PetRepository;
import br.com.api.repository.entities.Cliente;
import br.com.api.repository.entities.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class PetConversor implements Conversor<PetDTO, Pet> {

    @Autowired
    private ClienteConversor conversor;

    @Autowired
    private PetRepository repository;

    @Override
    public PetDTO converterParaDTO(Pet pet) {

        return PetDTO.builder()
                .petId(pet.getId())
                .dataNascimento(pet.getDataNascimento())
                .nome(pet.getNome())
                .peso(pet.getPeso())
                .porte(pet.getPorte())
                .clienteId(pet.getCliente().getId())
                .raca(pet.getRaca())
                .cor(pet.getCor())
                .sexo(pet.getSexo())
                .tipo(pet.getTipo())
                .build();
    }

    @Override
    public Pet converterParaEntidade(PetDTO dto) {

        return Pet.builder()
                .cor(dto.getCor())
                .dataNascimento(dto.getDataNascimento())
                .id(dto.getPetId())
                .nome(dto.getNome())
                .peso(dto.getPeso())
                .porte(dto.getPorte())
                .fgAtivo(true)
                .raca(dto.getRaca())
                .sexo(dto.getSexo())
                .tipo(dto.getTipo())
                .build();
    }

    public Pet converterParaEntidade(PetDTO dto, Cliente cliente) {
        final Pet pet = this.converterParaEntidade(dto);
        pet.setCliente(cliente);
        return pet;
    }

    public Pet converterParaEntidadeAtualizacao(PetDTO petDTO) {

        final Long petId = petDTO.getPetId();

        final Pet pet = repository.getOne(petId);

        pet.setTipo(petDTO.getTipo());
        pet.setSexo(petDTO.getSexo());
        pet.setRaca(petDTO.getRaca());
        pet.setPorte(petDTO.getPorte());
        pet.setPeso(petDTO.getPeso());
        pet.setNome(petDTO.getNome());
        pet.setDataNascimento(petDTO.getDataNascimento());
        pet.setCor(petDTO.getCor());

        return pet;
    }
}
