package br.com.fatec.user.model;

import br.com.fatec.user.model.dtos.UsuarioDto;
import br.com.fatec.user.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Table(name = "users")
@Entity(name = "User")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Setter
    @Column(unique = true)
    private String email;
    @Setter
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public static Usuario toEntity(UsuarioDto usuario, RoleRepository repository, BCryptPasswordEncoder encoder) {
        Usuario user = new Usuario(
                usuario.getId(),
                usuario.getName(),
                usuario.getEmail(),
                encoder.encode(usuario.getPassword()),
                new HashSet<>());
        usuario.getRoles().forEach(r -> user.getRoles().add(repository.findById(r.getId()).orElseThrow(
                () -> new RuntimeException("Role not found")
        )));
        return user;
    }
}
