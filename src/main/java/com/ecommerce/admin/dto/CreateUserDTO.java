package com.ecommerce.admin.dto;

import com.ecommerce.admin.entity.enums.ERole;
import lombok.Data;

import java.util.UUID;

@Data

public class CreateUserDTO {
private Long id;

    private String userName;
    private String userFirstName;
    private String userLastName;
    private String email;
    private String userPassword;

    private ERole role;

}
