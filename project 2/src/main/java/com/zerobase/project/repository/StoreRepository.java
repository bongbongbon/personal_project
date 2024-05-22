package com.zerobase.project.repository;

import com.zerobase.project.user.SiteUser;
import com.zerobase.project.user.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {


    Optional<Store> findByUsername(SiteUser user);

    Page<Store> findAll(Pageable pageable);

}
