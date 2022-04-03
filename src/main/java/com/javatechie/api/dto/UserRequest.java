package com.javatechie.api.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserRequest {
    @NotNull
    private String name;
    @Email(message = "invalid email id")
    private String email;
    @NotNull
    @Pattern(regexp = "^\\d{10}$",message = "invalid mobile number entered")
    private String mobile;
    private String gender;
    @Min(18)
    @Max(55)
    private int age;
    @NotBlank
    private String nationality;
}
