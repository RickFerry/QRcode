package br.com.fatec.user.repository;

import br.com.fatec.user.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
}