package br.com.fatec.user.model.dtos;

import br.com.fatec.user.model.Role;
import br.com.fatec.user.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private Long id;
    private String name;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Set<Role> roles = new HashSet<>();

    public static UsuarioDto toDto(Usuario usuario) {
        UsuarioDto user = new UsuarioDto(
                usuario.getId(),
                usuario.getName(),
                usuario.getEmail(),
                usuario.getPassword(),
                new HashSet<>());
        usuario.getRoles().forEach(r -> user.getRoles().add(r));
        return user;
    }
}
