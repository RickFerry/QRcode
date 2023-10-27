package br.com.fatec.user.service;

import br.com.fatec.user.model.Usuario;
import br.com.fatec.user.model.dtos.UsuarioDto;
import br.com.fatec.user.repository.RoleRepository;
import br.com.fatec.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.fatec.user.model.Usuario.toEntity;
import static br.com.fatec.user.model.dtos.UsuarioDto.toDto;

@Service
public class UserService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository repository, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional(readOnly = true)
    public Usuario findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Transactional
    public UsuarioDto create(UsuarioDto usuario) {
        return toDto(repository.save(
                toEntity(usuario, roleRepository, encoder)
        ));
    }
}
