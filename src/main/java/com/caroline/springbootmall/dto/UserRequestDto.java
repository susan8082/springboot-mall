package com.caroline.springbootmall.dto;

import com.caroline.springbootmall.model.Company;
import com.caroline.springbootmall.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserRequestDto {

    private Integer userId;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    public static User convertToUser(UserRequestDto userDto) {
        User user = new User();
        if(userDto.getUserId() != null){
            user.setUserId(userDto.getUserId());
        }else{
            user.setCreatedDate(LocalDate.now());
        }
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setLastModifiedTime(LocalDateTime.now());

        return user;
    }
}
