package br.com.fatec.user.service;

import br.com.fatec.user.model.Usuario;
import br.com.fatec.user.model.dto.UsuarioDto;
import br.com.fatec.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final BCryptPasswordEncoder encoder;
    private final UserRepository repository;

    public UserService(BCryptPasswordEncoder encoder, UserRepository repository) {
        this.encoder = encoder;
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional(readOnly = true)
    public Usuario findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public UsuarioDto create(UsuarioDto dto) {
        dto.setPassword(encoder.encode(dto.getPassword()));
        return new UsuarioDto(repository.save(new Usuario(dto)));
    }
}
