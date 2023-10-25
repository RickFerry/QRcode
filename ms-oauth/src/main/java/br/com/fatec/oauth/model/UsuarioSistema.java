package br.com.fatec.oauth.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@EqualsAndHashCode(callSuper = false)
public class UsuarioSistema extends User {

    private final Usuario usuario;

    public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
        super(usuario.getName(), usuario.getPassword(), authorities);
        this.usuario = usuario;
    }
}
