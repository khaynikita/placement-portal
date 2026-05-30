package com.nikita.placementportal.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Builder
public class User {
    @Id
    public String id;
    public String name;
    public String email;
    public String password;
    public String role;
    public String phone;
    public String profile_image;
    public String college;
    public String resume_url;
    public Date created_at;

}
