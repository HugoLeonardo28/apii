package br.com.api.config;

import br.com.api.error.exception.LoginInvalidoException;
import br.com.api.repository.UsuarioRepository;
import br.com.api.repository.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApiiUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario =
                repository.buscarPorEmail(email).orElseThrow(() ->
                        new LoginInvalidoException()
                );

        if(usuario.getFgAtivo()) {
            return new ApiiUser(usuario);
        } else {
            throw new LoginInvalidoException();
        }
    }
}
