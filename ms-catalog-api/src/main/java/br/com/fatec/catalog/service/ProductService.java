package br.com.fatec.catalog.service;

import br.com.fatec.catalog.dto.ProductDto;
import br.com.fatec.catalog.exception.ProductNotFoundException;
import br.com.fatec.catalog.mapper.ProductMapper;
import br.com.fatec.catalog.model.Product;
import br.com.fatec.catalog.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    @Transactional
    public ProductDto save(ProductDto productDto) {
        Product productToSave = productMapper.toModel(productDto);
        Product savedProduct = productRepository.save(productToSave);
        return productMapper.toDto(savedProduct);
    }

    public List<ProductDto> getProductsByUserId(UUID userId) {
        return productRepository.getProductsByUserId(userId)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductDto updateById(UUID id, ProductDto productDto) throws ProductNotFoundException {
        verifyIfProductExists(id);
        Product productToUpdate = productMapper.toModel(productDto);
        Product updatedProduct = productRepository.save(productToUpdate);
        return productMapper.toDto(updatedProduct);
    }

    @Transactional
    public void deleteById(UUID id) throws ProductNotFoundException {
        Product productToDelete = verifyIfProductExists(id);
        productRepository.delete(productToDelete);
    }

    private Product verifyIfProductExists(UUID id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
