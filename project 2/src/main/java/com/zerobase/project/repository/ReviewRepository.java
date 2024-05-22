package com.zerobase.project.repository;

import com.zerobase.project.user.Review;
import com.zerobase.project.user.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByStoreName(Store store);

}
