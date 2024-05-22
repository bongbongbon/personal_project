package com.zerobase.project.service;

import com.zerobase.project.model.ReviewCreateForm;
import com.zerobase.project.model.UserCreateForm;
import com.zerobase.project.repository.ReservationRepository;
import com.zerobase.project.repository.ReviewRepository;
import com.zerobase.project.repository.StoreRepository;
import com.zerobase.project.repository.UserRepository;
import com.zerobase.project.user.Reservation;
import com.zerobase.project.user.Review;
import com.zerobase.project.user.SiteUser;
import com.zerobase.project.user.Store;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    public List<Reservation> writeReview(SiteUser user) {


        List<Reservation> reservationList = findCompletedReservationsByUser(user);

        return reservationList;
    }

    public List<Reservation> findCompletedReservationsByUser(SiteUser user) {
        List<Reservation> userReservations = reservationRepository.findByUsername(user);
        return userReservations.stream()
                .filter(reservation -> "방문완료".equals(reservation.getStatus())) // 상태가 "방문완료"인 경우만 필터링
                .collect(Collectors.toList());
    }


    public void createReview(Long storeId, ReviewCreateForm reviewCreateForm) {

        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        SiteUser user = userRepository.findByusername(username)
                .orElseThrow(() -> new RuntimeException("세션만료"));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException());

        Review review = Review.builder()
                .username(user)
                .storeName(store)
                .subject(reviewCreateForm.getSubject())
                .context(reviewCreateForm.getContext())
                .build();

        reviewRepository.save(review);

    }
}
