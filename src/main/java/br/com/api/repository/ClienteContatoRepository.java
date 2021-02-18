package br.com.api.repository;

import br.com.api.repository.entities.ClienteContato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteContatoRepository extends JpaRepository<ClienteContato, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM cliente_contato WHERE cliente_id = :clienteId")
    public Optional<List<ClienteContato>> listarPorClienteId(Long clienteId);

    @Query(nativeQuery = true, value = "UPDATE cliente_contato SET FG_ATIVO = 0 WHERE contatoId = :contatoId")
    public void desativarContao(Long contatoId);

    public Optional<ClienteContato> findByIdAndFgAtivo(Long contatoId, Boolean fgAtivo);

    public Optional<List<ClienteContato>> findByFgAtivo(Boolean fgAtivo);
}
