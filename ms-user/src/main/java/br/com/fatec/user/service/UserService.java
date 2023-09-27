package br.com.fatec.user.service;

import br.com.fatec.user.model.Usuario;
import br.com.fatec.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Usuario findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Usuario findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
