package com.zerobase.project.service;

import com.zerobase.project.model.StoreCreateForm;
import com.zerobase.project.repository.StoreRepository;
import com.zerobase.project.repository.UserRepository;
import com.zerobase.project.user.SiteUser;
import com.zerobase.project.user.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final UserRepository userRepository;

    private final StoreRepository storeRepository;


    public Store create(StoreCreateForm storeCreateForm, SiteUser user) {

        Store store = Store.builder()
                .username(user)
                .storeName(storeCreateForm.getStoreName())
                .storeLocation(storeCreateForm.getStoreLocation())
                .storeDetailLocation(storeCreateForm.getStoreDetailLocation())
                .storeDescription(storeCreateForm.getStoreDescription())
                .createDate(LocalDateTime.now())
                .build();

        storeRepository.save(store);

        return store;
    }

    // 모든 상점 조회
    public List<Store> selectAll() {

        List<Store> stores = storeRepository.findAll();

        return stores;
    }

    // 특정 상점 조회
    public Store selectOne(Long storeId) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("상점이 존재하지 않습니다."));

        return store;
    }


    public void deleteOne(Long storeId) {

        storeRepository.deleteById(storeId);

    }

    public Store updateOne(Long storeId, StoreCreateForm storeCreateForm) {

        Store store = storeRepository.findById(storeId)
                        .orElseThrow(() -> new RuntimeException("상점이 없습니다."));
        store.setStoreName(storeCreateForm.getStoreName());
        store.setStoreLocation(storeCreateForm.getStoreLocation());
        store.setStoreDescription(storeCreateForm.getStoreDescription());


        storeRepository.save(store);

        return store;
    }

    public Page<Store> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.storeRepository.findAll(pageable);
    }
}
