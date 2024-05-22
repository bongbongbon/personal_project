package com.zerobase.project.controller;

import com.zerobase.project.model.StoreCreateForm;
import com.zerobase.project.repository.UserRepository;
import com.zerobase.project.service.StoreService;
import com.zerobase.project.user.SiteUser;
import com.zerobase.project.user.Store;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;
    private final UserRepository userRepository;


    @GetMapping("/create")
    public String createStore(StoreCreateForm storeCreateForm) {

        return "createStore_form";
    }

    // 상점 생성
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String createStore(@Valid StoreCreateForm storeCreateForm, BindingResult bindingResult, Principal principal) {

        if (bindingResult.hasErrors()) {
            return "createStore_form";
        }

        SiteUser user = userRepository.findByusername(principal.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 상태"));
        storeService.create(storeCreateForm, user);

        return "redirect:/store/list";
    }

    // 상점 전체 리스트
    @GetMapping("/selectAll")
    public String selectAll(Model model) {

        List<Store> stores = storeService.selectAll();
        model.addAttribute("stores", stores);

        return "storeList_form";
    }

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Store> paging = this.storeService.getList(page);
        model.addAttribute("paging", paging);
        return "storeList_form";
    }

    // 상점리스트에서 상점이름 조회
    @GetMapping("/detail/{id}")
    public String selectOne(@PathVariable(name = "id") Long storeId, Model model) {

        Store store = storeService.selectOne(storeId);
        model.addAttribute("store", store);

        return "storeDetail_form";
    }

    // 상점 삭제
    @PostMapping("/delete/{id}")
    public String deleteStore(@PathVariable(name = "id") Long storeId) {

        storeService.deleteOne(storeId);

        return "redirect:/store/list";
    }

    @GetMapping("/detail/update/{id}")
    public String updateStore(@PathVariable(name = "id") Long storeId, Model model) {

        Store store = storeService.selectOne(storeId);
        model.addAttribute("store", store);

        return "updateStore_form";
    }

    // 상점 수정
    @PostMapping("/update/{id}")
    public String updateStore(@PathVariable(name = "id") Long storeId) {


        return null;
    }


}
