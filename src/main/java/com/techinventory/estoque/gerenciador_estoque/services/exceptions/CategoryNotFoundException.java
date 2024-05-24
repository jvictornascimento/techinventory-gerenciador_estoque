package com.techinventory.estoque.gerenciador_estoque.services.exceptions;

public class CategoryNotFoundException extends RuntimeException{
    private static final long serialVersionUID =1L;

    public CategoryNotFoundException() {
        super("Category not found");
    }

    public CategoryNotFoundException(Object id) {
        super("Category not found. Id: "  + id);
    }
}
