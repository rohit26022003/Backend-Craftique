package com.example.craftwebsite.service;

import com.example.craftwebsite.model.Product;
import com.example.craftwebsite.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Optional<Product> getById(Long id) {
        return repo.findById(id);
    }

     public List<Product> getByCategory(String cate) {
         return repo.findByCategory(cate);
     }

     public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return repo.findByPriceBetween(minPrice, maxPrice);
    }


     public List<Product> getByName(String name) {
         return repo.findByName(name);
     }

    public Product save(Product product) {
        return repo.save(product);
    }

    public Product update(Long id, Product updatedProduct) {
        return repo.findById(id).map(existing -> {
            existing.setName(updatedProduct.getName());
            existing.setDescription(updatedProduct.getDescription());
            existing.setPrice(updatedProduct.getPrice());
            existing.setImagePath(updatedProduct.getImagePath());
            existing.setcategory(updatedProduct.getcategory());
            existing.setstock(updatedProduct.getstock());
            return repo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

     public List<String> getUniqueCategories() {
        return repo.findDistinctCategories()
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }

}