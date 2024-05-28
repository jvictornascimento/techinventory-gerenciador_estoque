package com.techinventory.estoque.gerenciador_estoque.services.exceptions;

public class ClientNotFoundException extends RuntimeException{
    private static final long serialVersionUID =1L;

    public ClientNotFoundException() {
        super("Client not found");
    }

    public ClientNotFoundException(Object id) {
        super("Client not found. Id: "  + id);
    }
}
