package br.com.fatec.catalog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductCategory {

    PADARIA("Padaria"),
    HORTIFRUTI("Hortifruti"),
    FRIOS("Congelados e frios"),
    LIMPEZA("Produtos de limpeza"),
    HIGIENE("Higiene pessoal"),
    BEBIDA("Bebidas"),
    PAPELARIA("Papelaria");

    private final String description;
}
