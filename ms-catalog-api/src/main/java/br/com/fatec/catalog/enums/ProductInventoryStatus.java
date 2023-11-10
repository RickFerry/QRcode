package br.com.fatec.catalog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductInventoryStatus {

    INSTOCK,
    LOWSTOCK,
    OUTOFSTOCK
}
