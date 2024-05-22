package com.zerobase.project.model;


import com.zerobase.project.user.SiteUser;
import com.zerobase.project.user.Store;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateForm {

    private Long storeId;

    @NotEmpty(message = "제목을 적어주세요")
    private String subject;

    @NotEmpty(message = "내용을 적어주세요")
    private String context;
}
