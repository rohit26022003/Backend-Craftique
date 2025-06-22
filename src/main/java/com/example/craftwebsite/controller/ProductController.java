package com.example.craftwebsite.controller;

import com.example.craftwebsite.model.Product;
import com.example.craftwebsite.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    //get all products
    @GetMapping("/products")
    public List<Product> getAll() {
        return service.getAll();
    }
    
    //get product by id
    @GetMapping("/product/{id}")
    public Optional<Product> getById(@PathVariable Long id) {
        return service.getById(id);
    }
    
    // add product
    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product) throws IOException {
        product.setName(product.getName());
        product.setDescription(product.getDescription());
        product.setPrice(product.getPrice());
        product.setImagePath(product.getImagePath());
        product.setcategory(product.getcategory());
        product.setstock(product.getstock());
        return service.save(product);
    }
    
    //update product
    @PutMapping("/product/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return service.update(id, product);
    }
    
    //delete product
    @DeleteMapping("/product/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Product deleted.";
    }
    
    // find by category
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return service.getByCategory(category);
    }
    // find by price
    
    public List<Product> getProductsByPriceRange(
        @RequestParam Double minPrice,
        @RequestParam Double maxPrice) {
        return service.getProductsByPriceRange(minPrice, maxPrice);
    }

    // find by name
     @GetMapping("/name/{name}")
    public List<Product> getProductsByName(@PathVariable String name) {
        return service.getByName(name);
    }


    @GetMapping("/unique-categories")
    public List<String> getUniqueCategories() {
        return service.getUniqueCategories();
    }

}