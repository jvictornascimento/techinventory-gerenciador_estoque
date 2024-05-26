package com.techinventory.estoque.gerenciador_estoque.dtos.category;

import com.techinventory.estoque.gerenciador_estoque.model.Product;
import com.techinventory.estoque.gerenciador_estoque.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public ResponseEntity<Product> getOneProduct(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.findById(id));
    }

}
