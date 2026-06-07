package com.nikita.placementportal.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSummaryDTO {
    private String id;
    private String name;
    private String email;
    private String role;
    private String phone;
}
