package com.techinventory.estoque.gerenciador_estoque.services.exceptions;

public class OrderNotFoundException extends RuntimeException{
    private static final long serialVersionUID =1L;

    public OrderNotFoundException() {
        super("Order not found");
    }

    public OrderNotFoundException(Object id) {
        super("Order not found. Id: "  + id);
    }
}
