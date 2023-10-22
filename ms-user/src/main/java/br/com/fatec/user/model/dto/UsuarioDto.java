package br.com.fatec.user.model.dto;

import br.com.fatec.user.model.Role;
import br.com.fatec.user.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Builder @Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsuarioDto {

    @Setter
    Long id;

    @Setter
    String name;

    @Setter
    String email;

    @Setter
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    Set<Role> roles = new HashSet<>();

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.name = usuario.getName();
        this.email = usuario.getEmail();
        this.password = usuario.getPassword();
        roles.addAll(usuario.getRoles());
    }
}
