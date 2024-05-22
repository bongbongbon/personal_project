package com.zerobase.project.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "username")
    @ManyToOne
    private SiteUser username;

    @Column
    private String storeName;

    @Column
    private String storeLocation;

    @Column
    private String storeDetailLocation;

    @Column
    private String storeDescription;

    @Column
    private LocalDateTime createDate;

}
