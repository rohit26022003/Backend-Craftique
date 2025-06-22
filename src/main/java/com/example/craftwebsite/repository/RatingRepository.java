// repository/RatingRepository.java
package com.example.craftwebsite.repository;

import com.example.craftwebsite.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
