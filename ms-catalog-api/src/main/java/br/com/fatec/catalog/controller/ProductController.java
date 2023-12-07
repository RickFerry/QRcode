package br.com.fatec.catalog.controller;

import br.com.fatec.catalog.dto.ProductDto;
import br.com.fatec.catalog.exception.ProductNotFoundException;
import br.com.fatec.catalog.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
@CrossOrigin("http://localhost:4200")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto save(@RequestBody @Valid ProductDto productDto) {
        return productService.save(productDto);
    }

    @GetMapping("/{userId}")
    public List<ProductDto> getProductsByUserId(@PathVariable UUID userId) {
        return productService.getProductsByUserId(userId);
    }

    @PutMapping("/{id}")
    public ProductDto updateById(@PathVariable UUID id, @RequestBody @Valid ProductDto productDto) throws ProductNotFoundException {
        return productService.updateById(id, productDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id) throws ProductNotFoundException {
        productService.deleteById(id);
    }
}
