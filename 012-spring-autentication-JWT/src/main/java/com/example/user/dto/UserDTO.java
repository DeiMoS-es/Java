package com.example.user.dto;

import com.example.user.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    Integer id;
    String username;
    String lastname;
    String firstname;
    String country;
    Role role;
}
