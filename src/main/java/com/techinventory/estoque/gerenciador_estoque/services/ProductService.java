package com.techinventory.estoque.gerenciador_estoque.services;

import com.techinventory.estoque.gerenciador_estoque.dtos.category.ProductController;
import com.techinventory.estoque.gerenciador_estoque.model.Product;
import com.techinventory.estoque.gerenciador_estoque.repositories.ProductRepository;
import com.techinventory.estoque.gerenciador_estoque.services.exceptions.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(UUID id){
        return productRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
    }
}
