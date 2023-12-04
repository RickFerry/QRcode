package br.com.fatec.user.model.dto;

import br.com.fatec.user.model.Role;
import br.com.fatec.user.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder @Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    UUID id;
    String name;
    String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    Set<Role> roles = new HashSet<>();

    public static UserDto toDto(User usuario) {
        UserDto user = UserDto.builder()
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
