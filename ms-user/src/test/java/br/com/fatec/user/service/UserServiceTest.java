package br.com.fatec.user.service;

import br.com.fatec.user.model.User;
import br.com.fatec.user.model.dto.UsuarioDto;
import br.com.fatec.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private BCryptPasswordEncoder encoder;
    @Mock
    private UserRepository userRepository;

    private User usuario;
    private UsuarioDto dto;

    @BeforeEach
    void setUp() {
        initMocks(this);
        StartObjects();
    }

    @Test
    void whenFindByIdThenReturnUser() {
        when(userRepository.findById(any())).thenReturn(Optional.of(usuario));
        UsuarioDto resp = userService.findById(any());
        assertEquals(UsuarioDto.class, resp.getClass());
    }

    @Test
    void whenFindByIdThenReturnRuntimeException() {
        when(userRepository.findById(any())).thenThrow(new RuntimeException());
        try {
            userService.findById(any());
        } catch (Exception e) {
            assertEquals(RuntimeException.class, e.getClass());
        }
    }

    @Test
    void whenFindAllThenReturnListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(usuario));
        List<UsuarioDto> resp = userService.findAll();
        assertEquals(UsuarioDto.class, resp.get(0).getClass());
    }

    @Test
    void whenFindByEmailThenReturnUser() {
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(usuario));
        UsuarioDto resp = userService.findByEmail(any());
        assertEquals(usuario.getEmail(), resp.getEmail());
    }

    @Test
    void whenFindByEmailThenReturnRuntimeException() {
        when(userRepository.findByEmail(any())).thenThrow(new RuntimeException());
        try {
            userService.findByEmail(any());
        } catch (Exception e) {
            assertEquals(RuntimeException.class, e.getClass());
        }
    }

    @Test
    void whenCreateThenReturnUserCreated() {
        when(userRepository.save(any())).thenReturn(usuario);
        UsuarioDto resp = userService.create(dto);
        assertEquals(usuario.getId(), resp.getId());
    }

    private void StartObjects() {
        usuario = User.builder()
                .id(valueOf(1))
                .name("Fulano")
                .email("fulano@fulano.com")
                .password("123")
                .roles(new HashSet<>())
                .build();

        dto = UsuarioDto.builder()
                .id(valueOf(1))
                .name("Fulano")
                .email("fulano@fulano.com")
                .password("123")
                .roles(new HashSet<>())
                .build();
    }
}