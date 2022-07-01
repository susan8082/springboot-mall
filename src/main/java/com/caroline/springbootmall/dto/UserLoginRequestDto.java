package com.caroline.springbootmall.dto;

import com.caroline.springbootmall.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserLoginRequestDto {

    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;

}
