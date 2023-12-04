package br.com.fatec.user.service;

import br.com.fatec.user.model.User;
import br.com.fatec.user.model.dto.UserDto;
import br.com.fatec.user.repository.RoleRepository;
import br.com.fatec.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import static br.com.fatec.user.model.User.toEntity;
import static br.com.fatec.user.model.dto.UserDto.toDto;
import static java.util.Objects.isNull;

@Service
public class UserService {
    private final BCryptPasswordEncoder encoder;
    private final UserRepository repository;
    private final RoleRepository roleRepository;

    public UserService(BCryptPasswordEncoder encoder, UserRepository repository, RoleRepository roleRepository) {
        this.encoder = encoder;
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    public UserDto findById(UUID id) {
        return toDto(
                repository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"))
        );
    }

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return repository.findAll().stream().map(UserDto::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Transactional
    public UserDto create(UserDto dto) {
        User entity = toEntity(dto, roleRepository, encoder);
        if (isNull(entity.getId())) {
            entity.setId(UUID.randomUUID());
        }
        return toDto(repository.save(entity));
    }
}
