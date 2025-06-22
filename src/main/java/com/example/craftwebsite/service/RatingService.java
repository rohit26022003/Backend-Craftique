// service/RatingService.java
package com.example.craftwebsite.service;

import com.example.craftwebsite.model.Rating;
import com.example.craftwebsite.repository.RatingRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    private final RatingRepository repository;

    public RatingService(RatingRepository repository) {
        this.repository = repository;
    }

    public Rating addRating(Long productId, Rating rating) {
        rating.setProductId(productId);
        return repository.save(rating);
    }
}
