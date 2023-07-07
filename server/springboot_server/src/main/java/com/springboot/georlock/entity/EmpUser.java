package com.springboot.georlock.entity;

import com.springboot.georlock.enums.RoleType;
import com.springboot.georlock.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMP_USERS")
public class EmpUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String empNo;

    private String username;

    private String password;

    private String inTime;

    private String outTime;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    private String token;

    private String nfc;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
}
