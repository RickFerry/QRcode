package br.com.fatec.user.controller;

import br.com.fatec.user.model.User;
import br.com.fatec.user.model.dto.UserDto;
import br.com.fatec.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') and #oauth2.hasScope('read')")
    public ResponseEntity<UserDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<User> findByEmail(@RequestParam String email) {
        return ResponseEntity.ok(service.findByEmail(email));
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto dto, UriComponentsBuilder uriComponentsBuilder) {
        try {
            UserDto resp = service.create(dto);
            return ResponseEntity
                    .created(uriComponentsBuilder.path("/users/{id}").buildAndExpand(resp.getId())
                            .toUri()).body(resp);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
