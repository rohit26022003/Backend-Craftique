package com.example.craftwebsite.controller;

import com.example.craftwebsite.model.Review;
import com.example.craftwebsite.model.Rating;
import com.example.craftwebsite.service.ReviewService;
import com.example.craftwebsite.service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;
    private final RatingService ratingService;
    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    public ReviewController(ReviewService reviewService, RatingService ratingService) {
        this.reviewService = reviewService;
        this.ratingService = ratingService;
    }

    @PostMapping("/review/product/{productId}")
    public ResponseEntity<Review> addReview(@PathVariable Long productId, @RequestBody Review review) {
        logger.info("Received review for product {}: {}", productId, review);
        review.setProductId(productId);
        Review savedReview = reviewService.addReview(productId, review);
        return ResponseEntity.ok(savedReview);
    }

    @GetMapping("/reviews/product/{productId}")
    public ResponseEntity<List<Review>> getReviews(@PathVariable Long productId) {
        logger.info("Fetching reviews for product {}", productId);
        List<Review> reviews = reviewService.getReviewsByProduct(productId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/rating/product/{productId}")
    public ResponseEntity<Rating> addRating(@PathVariable Long productId, @RequestBody Rating rating) {
        logger.info("Received rating for product {}: {}", productId, rating);
        rating.setProductId(productId);
        Rating savedRating = ratingService.addRating(productId, rating);
        return ResponseEntity.ok(savedRating);
    }
}
