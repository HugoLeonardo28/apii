package br.com.api.repository;

import br.com.api.repository.entities.ClienteEndereco;
import br.com.api.repository.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM USUARIO WHERE EMAIL = :email")
    public Optional<Usuario> buscarPorEmail(String email);
}
