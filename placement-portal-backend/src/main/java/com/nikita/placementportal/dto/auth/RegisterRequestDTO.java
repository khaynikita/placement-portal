package com.nikita.placementportal.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterRequestDTO {


    @NotBlank
    public String name;
    @NotBlank
    public String email;
    @NotBlank
    public String password;
    public String role;
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    public String phone;
}
