package com.zerobase.project.repository;

import com.zerobase.project.user.Reservation;
import com.zerobase.project.user.SiteUser;
import com.zerobase.project.user.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByStoreName(Store store);

    List<Reservation> findByUsername(SiteUser user);
}
