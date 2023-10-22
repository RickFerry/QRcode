package br.com.fatec.user.model;

import br.com.fatec.user.model.dto.UsuarioDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Builder @Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity(name = "User")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Usuario {

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

    public Usuario(UsuarioDto dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        roles.addAll(dto.getRoles());
    }
}
