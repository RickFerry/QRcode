package br.com.fatec.catalog.model;

import br.com.fatec.catalog.enums.ProductCategory;
import br.com.fatec.catalog.enums.ProductInventoryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product extends UUIDModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private BigDecimal price;
    private String description;
    private ProductCategory category;
    private ProductInventoryStatus inventoryStatus;
    private UUID userId;
}
