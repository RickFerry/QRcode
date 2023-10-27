package br.com.fatec.user.model;

import br.com.fatec.user.model.dto.UserDto;
import br.com.fatec.user.repository.RoleRepository;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder @Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter @NotNull
    String name;

    @Setter
    @Column(unique = true)
    String email;

    @Setter @NotNull
    String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
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
                () -> new RuntimeException("Not found!")
        )));
        return user;
    }
}
