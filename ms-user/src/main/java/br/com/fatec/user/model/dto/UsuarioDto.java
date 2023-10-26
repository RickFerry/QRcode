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

    Long id;
    String name;
    String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    Set<Role> roles = new HashSet<>();

    public static UsuarioDto toDto(Usuario usuario) {
        UsuarioDto user = UsuarioDto.builder()
                .id(usuario.getId())
                .name(usuario.getName())
                .email(usuario.getEmail())
                .password(usuario.getPassword())
                .roles(new HashSet<>())
                .build();
        usuario.getRoles().forEach(r -> user.getRoles().add(r));
        return user;
    }
}
