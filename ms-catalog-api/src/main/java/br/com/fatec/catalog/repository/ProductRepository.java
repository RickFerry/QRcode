package br.com.fatec.catalog.repository;

import br.com.fatec.catalog.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends MongoRepository<Product, UUID> {

    List<Product> getProductsByUserId(Long userId);
}
