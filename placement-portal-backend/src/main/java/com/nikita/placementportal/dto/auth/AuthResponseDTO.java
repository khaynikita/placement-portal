package com.nikita.placementportal.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {

    private String token;
    private String userId;
    private String name;
    private String email;
    private String role;

}