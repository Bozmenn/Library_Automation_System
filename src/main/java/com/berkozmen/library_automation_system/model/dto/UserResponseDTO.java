package com.berkozmen.library_automation_system.model.dto;

import com.berkozmen.library_automation_system.model.Role;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {

    private Integer id;
    private String username;
    private String email;
    private List<Role> roles;

}
