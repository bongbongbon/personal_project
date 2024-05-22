package com.zerobase.project.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "username")
    @ManyToOne
    private SiteUser username;

    @JoinColumn(name = "storeName")
    @ManyToOne
    private Store storeName;

    private String subject;

    private String context;

}
