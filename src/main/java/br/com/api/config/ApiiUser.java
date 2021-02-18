package br.com.api.config;

import br.com.api.repository.entities.Usuario;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

public class ApiiUser extends User {

    public ApiiUser(Usuario usuario) {
        super(usuario.getEmail(), usuario.getSenha(), Collections.emptyList());
    }
}
