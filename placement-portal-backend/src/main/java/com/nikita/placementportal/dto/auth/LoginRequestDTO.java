package com.nikita.placementportal.dto.auth;

import lombok.Data;

@Data
public class LoginRequestDTO {
    public String email;
    public String password;
}
