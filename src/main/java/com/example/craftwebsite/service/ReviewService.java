package com.example.craftwebsite.service;

import com.example.craftwebsite.model.Review;
import com.example.craftwebsite.repository.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public Review addReview(Long productId, Review review) {
        if (review == null) {
            logger.warn("Attempted to save a null review for productId {}", productId);
            throw new IllegalArgumentException("Review cannot be null");
        }

        review.setProductId(productId);
        Review savedReview = repository.save(review);
        logger.info("Saved review: {}", savedReview);
        return savedReview;
    }

    public List<Review> getReviewsByProduct(Long productId) {
        logger.info("Fetching reviews for productId {}", productId);
        return repository.findByProductId(productId);
    }
}
