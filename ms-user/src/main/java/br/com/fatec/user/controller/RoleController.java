package br.com.fatec.user.controller;

import br.com.fatec.user.model.dto.RoleDto;
import br.com.fatec.user.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleDto> create(@RequestBody RoleDto dto) {
        return ResponseEntity.ok().body(roleService.create(dto));
    }
}
