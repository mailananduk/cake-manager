package com.wracle.cakemgr.cakemanager.io.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "users")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 3916574419850680650L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "EMAILID", unique = true, nullable = false, length = 100)
    private String emailId;

    @Column(name = "ENCRYPTEDPASSWORD", unique = false, nullable = false, length = 100)
    private String encryptedPassword;
}

