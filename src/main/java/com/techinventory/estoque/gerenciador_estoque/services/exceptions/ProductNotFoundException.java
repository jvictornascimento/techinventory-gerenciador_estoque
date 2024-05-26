package com.techinventory.estoque.gerenciador_estoque.services.exceptions;

public class ProductNotFoundException extends RuntimeException{
    private static final long serialVersionUID =1L;

    public ProductNotFoundException() {
        super("Product not found");
    }

    public ProductNotFoundException(Object id) {
        super("Priduct not found. Id: "  + id);
    }
}
