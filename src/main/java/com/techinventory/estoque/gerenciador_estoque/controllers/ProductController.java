package com.techinventory.estoque.gerenciador_estoque.controllers;

import com.techinventory.estoque.gerenciador_estoque.dtos.products.ProductDTO;
import com.techinventory.estoque.gerenciador_estoque.dtos.products.ProductUpdateDTO;
import com.techinventory.estoque.gerenciador_estoque.model.Product;
import com.techinventory.estoque.gerenciador_estoque.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product")

public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getOneProduct(@PathVariable(value = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.findById(id));
    }
    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid ProductDTO productDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.save(productDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProductUpdateDTO productUpdateDTO ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.edit(productUpdateDTO,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.delete(id));
    }


}
