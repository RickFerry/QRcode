package br.com.fatec.user.controller;

import br.com.fatec.user.model.Usuario;
import br.com.fatec.user.model.dto.UsuarioDto;
import br.com.fatec.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') and #oauth2.hasScope('read')")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<Usuario> findByEmail(@RequestParam String email) {
        return ResponseEntity.ok(service.findByEmail(email));
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> create(@RequestBody UsuarioDto dto,
                                             @NotNull UriComponentsBuilder uriComponentsBuilder) {
        try {
            return ResponseEntity
                    .created(uriComponentsBuilder.path("/users/{id}").buildAndExpand(service.create(dto).getId())
                            .toUri()).body(service.create(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
