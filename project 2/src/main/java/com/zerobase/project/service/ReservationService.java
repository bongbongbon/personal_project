package com.zerobase.project.service;

import com.zerobase.project.repository.ReservationRepository;
import com.zerobase.project.repository.StoreRepository;
import com.zerobase.project.repository.UserRepository;
import com.zerobase.project.user.Reservation;
import com.zerobase.project.user.SiteUser;
import com.zerobase.project.user.Store;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;


    public String createReservation(Long storeId, LocalTime time) {

        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        SiteUser user = userRepository.findByusername(username)
                .orElseThrow(() -> new RuntimeException("세션만료"));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("상점아이디 없어"));

        Reservation reservation = Reservation.builder()
                .username(user)
                .storeName(store)
                .reservationTime(time)
                .status("방문전")
                .build();

        reservationRepository.save(reservation);

        return "ok";
    }

    public List<Reservation> viewReservation() {

        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        SiteUser user = userRepository.findByusername(username)
                .orElseThrow(() -> new RuntimeException());

        Store store = storeRepository.findByUsername(user)
                .orElseThrow(() -> new RuntimeException("상점아이디 없어"));

        List<Reservation> reservationList =  reservationRepository.findByStoreName(store);

        return reservationList;
    }


    public void changeStatus(Long reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId)
            .orElseThrow(() -> new RuntimeException());

        reservation.setStatus("방문완료");

        reservationRepository.save(reservation);
    }
}
