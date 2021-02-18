package br.com.api.repository;

import br.com.api.repository.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(nativeQuery = true, value = "UPDATE CLIENTE SET FG_ATIVO = 0 WHERE CLIENTE_ID = :clienteId")
    public void desativarCliente(Long clienteId);

    public Optional<List<Cliente>> findByFgAtivo(Boolean fgAtivo);
    public Optional<Cliente> findByIdAndFgAtivo(Long clienteId, Boolean fgAtivo);

}
