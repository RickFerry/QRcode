package br.com.fatec.oauth.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@EqualsAndHashCode(callSuper = false)
public class SystemUser extends org.springframework.security.core.userdetails.User {

    private final User usuario;

    public SystemUser(User usuario, Collection<? extends GrantedAuthority> authorities) {
        super(usuario.getName(), usuario.getPassword(), authorities);
        this.usuario = usuario;
    }
}
