package com.zerobase.project.controller;

import com.zerobase.project.model.ReviewCreateForm;
import com.zerobase.project.repository.ReservationRepository;
import com.zerobase.project.repository.ReviewRepository;
import com.zerobase.project.repository.StoreRepository;
import com.zerobase.project.repository.UserRepository;
import com.zerobase.project.service.ReviewService;
import com.zerobase.project.user.Reservation;
import com.zerobase.project.user.Review;
import com.zerobase.project.user.SiteUser;
import com.zerobase.project.user.Store;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final StoreRepository storeRepository;
    private final ReservationRepository reservationRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/write")
    public String reviewWrite(Model model, Principal principal) {

        SiteUser user = userRepository.findByusername(principal.getName())
                .orElseThrow(() -> new RuntimeException("로그인을 해주세요"));

        List<Reservation> reservations = reviewService.writeReview(user);
        model.addAttribute("reservations", reservations);

        return "reviewStore_form";
    }

    @GetMapping("/create/{id}")
    public String createReview(@PathVariable(name = "id") Long reservationId, Model model) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException());

        Store store = reservation.getStoreName();

        model.addAttribute("store", store);


        return "createReview_form";
    }

    @PostMapping("/create")
    public String createReview(@Valid ReviewCreateForm reviewCreateForm) {

        reviewService.createReview(reviewCreateForm.getStoreId(), reviewCreateForm);

        return "redirect:/home";
    }


    @GetMapping("/list/{id}")
    public String reviewList(@PathVariable(name = "id")Long storeId, Model model) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException());


        List<Review> reviews = reviewRepository.findByStoreName(store);

        model.addAttribute("reviews", reviews);

        return "reviewList_form";

    }

    @GetMapping("/detail/{id}")
    public String reviewDetail(@PathVariable(name = "id")Long reviewId, Model model) {

        Review review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new RuntimeException());

        model.addAttribute("review", review);

        return "reviewDetail_form";
    }

    @PostMapping("/delete/{id}")
    public String deleteReview(@PathVariable(name = "id")Long reviewId) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException());

        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        String reviewUsername = review.getUsername().getUsername();

        String reviewStoreName = review.getStoreName().getUsername().getUsername();

        if (username.equals(reviewUsername) || username.equals(reviewStoreName)) {
            reviewRepository.deleteById(reviewId);
            return "redirect:/home";
        }else {
            new RuntimeException("유저랑 상점주만 삭제가능");
            return "redirect:/reviewDetail_form";
        }

    }

}
