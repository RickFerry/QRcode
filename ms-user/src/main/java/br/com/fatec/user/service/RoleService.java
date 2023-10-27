package br.com.fatec.user.service;

import br.com.fatec.user.model.Role;
import br.com.fatec.user.model.dto.RoleDto;
import br.com.fatec.user.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public RoleDto create(RoleDto role) {
        Role r = new Role();
        copyProperties(role, r, "id");
        copyProperties(roleRepository.save(r), role);
        return role;
    }
}
