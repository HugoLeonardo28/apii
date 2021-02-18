package br.com.api.repository;

import br.com.api.dto.PetDTO;
import br.com.api.repository.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM PET P INNER JOIN CLIENTE_PET CP ON P.PET_ID = CP.PET_ID WHERE CP.CLIENTE_iD = :clienteId AND FG_ATIVO = 1")
    public Optional<List<Pet>> buscarPorClienteId(Long clienteId);

    @Query(nativeQuery = true, value = "UPDATE PET SET FG_ATIVO = 0 WHERE PET_ID = :petId")
    public void desativarPet(Long petId);

    public Optional<Pet> findByIdAndFgAtivo(Long petId, Boolean fgAtivo);

    public Optional<List<Pet>> findByFgAtivo(Boolean fgAtivo);

    @Query(nativeQuery = true, value = "INSERT INTO CLIENTE_PET VALUES (:clienteId, :petId)")
    public void vincularRelacionamento(Long petId, Long clienteId);

}
