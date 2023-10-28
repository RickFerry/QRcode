package br.com.fatec.user.model;

import br.com.fatec.user.model.dto.UserDto;
import br.com.fatec.user.repository.RoleRepository;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Document
@Builder @Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id @Setter
    String id;

    @Setter @NotNull
    String name;

    @Setter @NotNull
    String email;

    @Setter @NotNull
    String password;

    @DBRef
    Set<Role> roles = new HashSet<>();

    public static User toEntity(UserDto dto, RoleRepository repository, BCryptPasswordEncoder encoder) {
        User user = User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .roles(new HashSet<>())
                .build();
        dto.getRoles().forEach(r -> user.getRoles().add(repository.findById(r.getId()).orElseThrow(
                () -> new RuntimeException("Role not found")
        )));
        return user;
    }
}
