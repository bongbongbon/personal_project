package com.zerobase.project.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "username")
    @ManyToOne
    private SiteUser username;

    @JoinColumn(name = "storeName")
    @ManyToOne
    private Store storeName;

    @Column
    private LocalTime reservationTime;

    @Column
    private String status;

}
