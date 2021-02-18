package br.com.api.repository;

import br.com.api.repository.entities.ClienteEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteEnderecoRepository extends JpaRepository<ClienteEndereco, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM cliente_endereco WHERE CLIENTE_ID = :clienteId")
    public Optional<List<ClienteEndereco>> listarPorClienteId(Long clienteId);

    @Query(nativeQuery = true, value = "UPDATE cliente_endereco SET FG_ATIVO = 0 WHERE endereco_id = :clienteEnderecoId")
    public void desativarEndereco(Long clienteEnderecoId);

    public Optional<ClienteEndereco> findByIdAndFgAtivo(Long enderecoId, Boolean fgAtivo);

    public Optional<List<ClienteEndereco>> findByFgAtivo(Boolean fgaAtivo);
}
