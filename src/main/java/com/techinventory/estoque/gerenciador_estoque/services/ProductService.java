package com.techinventory.estoque.gerenciador_estoque.services;

import com.techinventory.estoque.gerenciador_estoque.dtos.products.ProductDTO;
import com.techinventory.estoque.gerenciador_estoque.dtos.products.ProductUpdateDTO;
import com.techinventory.estoque.gerenciador_estoque.model.Category;
import com.techinventory.estoque.gerenciador_estoque.model.Product;
import com.techinventory.estoque.gerenciador_estoque.repositories.ProductRepository;
import com.techinventory.estoque.gerenciador_estoque.services.exceptions.CategoryNotFoundException;
import com.techinventory.estoque.gerenciador_estoque.services.exceptions.ProductNotFoundException;
import org.springframework.beans.BeanUtils;
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

    public Product save(ProductDTO productDTO){
        var product = new Product();
        BeanUtils.copyProperties(productDTO,product);
        return productRepository.save(product);
    }

    public Product edit(ProductUpdateDTO productUpdateDTO, UUID id){
        return productRepository.save(updateData(productUpdateDTO,id));
    }

    public String delete(UUID id){
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return "product deleted sucessfully";
        }
        throw new ProductNotFoundException("Product not found");
    }

    private Product updateData(ProductUpdateDTO productUpdateDTO, UUID id){
        var data = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        data.setDescription((productUpdateDTO.description()!=null&&!productUpdateDTO.description().isEmpty())?productUpdateDTO.description():data.getDescription());
        data.setQuantity((productUpdateDTO.quantity()!=null)?productUpdateDTO.quantity(): data.getQuantity());
        data.setPrice((productUpdateDTO.price()!=null)?productUpdateDTO.price(): data.getPrice());
        data.setCategory((productUpdateDTO.category()!=null)?productUpdateDTO.category():data.getCategory());
        return data;
    }

}
