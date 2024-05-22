package com.zerobase.project.controller;

import com.zerobase.project.repository.ReservationRepository;
import com.zerobase.project.repository.StoreRepository;
import com.zerobase.project.service.ReservationService;
import com.zerobase.project.user.Reservation;
import com.zerobase.project.user.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final StoreRepository storeRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;


    @GetMapping("/{id}")
    public String makeReservation(@PathVariable("id") Long storeId, Model model) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException());

        model.addAttribute("store", store);


        return "reservation_form";
    }



    @PostMapping("/create/{id}")
    public String makeReservation(@PathVariable(name = "id") Long storeId, @RequestParam("time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime time) {

        reservationService.createReservation(storeId, time);

        return "redirect:/home";
    }

    @GetMapping("/list")
    public String viewReservation(Model model){

        List<Reservation> reservationList = reservationService.viewReservation();
        model.addAttribute("reservations", reservationList);

        return "/reservationList_form";
    }

    @PostMapping("/status/{id}")
    public String statusChange(@PathVariable(name = "id") Long reservationId) {

        reservationService.changeStatus(reservationId);

        return "redirect:/home";
    }




}
