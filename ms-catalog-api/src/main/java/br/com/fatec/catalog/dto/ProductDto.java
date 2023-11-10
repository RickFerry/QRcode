package br.com.fatec.catalog.dto;

import br.com.fatec.catalog.enums.ProductCategory;
import br.com.fatec.catalog.enums.ProductInventoryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private UUID id;
    @NotBlank
    private String name;
    @NotNull
    @Min(0)
    private BigDecimal price;
    @NotBlank
    private String description;
    private ProductCategory category;
    private ProductInventoryStatus inventoryStatus;
    @NotNull
    private Long userId;
}
