package com.techinventory.estoque.gerenciador_estoque.services;

import com.techinventory.estoque.gerenciador_estoque.controllers.CategoryController;
import com.techinventory.estoque.gerenciador_estoque.controllers.ProductController;
import com.techinventory.estoque.gerenciador_estoque.dtos.products.ProductDTO;
import com.techinventory.estoque.gerenciador_estoque.dtos.products.ProductUpdateDTO;
import com.techinventory.estoque.gerenciador_estoque.model.Product;
import com.techinventory.estoque.gerenciador_estoque.repositories.CategoryRepository;
import com.techinventory.estoque.gerenciador_estoque.repositories.ProductRepository;
import com.techinventory.estoque.gerenciador_estoque.services.exceptions.CategoryNotFoundException;
import com.techinventory.estoque.gerenciador_estoque.services.exceptions.ProductNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service

public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> findAll(){
       var products = productRepository.findAll();
        for (Product product : products){
            UUID id = product.getId();
            product.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
            product.getCategory().add(linkTo(methodOn(CategoryController.class).getOneCategory(product.getCategory().getId())).withSelfRel());
        }
        return products;
    }

    public Product findById(UUID id){
        var product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        product.add(linkTo(methodOn(ProductController.class).getAllProduct()).withRel("Product List"));
        product.getCategory().add(linkTo(methodOn(CategoryController.class).getOneCategory(product.getCategory().getId())).withSelfRel());
        return product;

    }

    public Product save(ProductDTO productDTO){
        UUID idCategory  = productDTO.category().getId();
        if (!categoryRepository.existsById(idCategory)){
            throw new CategoryNotFoundException();
        }
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
