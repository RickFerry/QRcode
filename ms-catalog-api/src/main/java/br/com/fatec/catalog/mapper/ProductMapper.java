package br.com.fatec.catalog.mapper;

import br.com.fatec.catalog.dto.ProductDto;
import br.com.fatec.catalog.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toModel(ProductDto productDto);
    ProductDto toDto(Product product);
    List<ProductDto> toList(List<Product> products);
}
