package com.zerobase.project.model;

import com.zerobase.project.user.SiteUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreCreateForm {

    @NotEmpty(message = "상점이름은 필수항목입니다.")
    private String storeName;

    @NotEmpty(message = "주소는 필수항목입니다.")
    private String storeLocation;

    @NotEmpty(message = "상세주소는 필수항목입니다.")
    private String storeDetailLocation;

    @NotEmpty(message = "상점상세내용은 필수항목입니다.")
    private String storeDescription;
}
