package com.example.craftwebsite.repository;

import com.example.craftwebsite.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    /**
     * Finds all reviews for the specified product ID.
     *
     * @param productId the ID of the product
     * @return list of reviews related to the given product ID
     */
    List<Review> findByProductId(Long productId);
}
