package com.wracle.cakemgr.cakemanager.repository.dao;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "EMAILID", unique = true, nullable = false, length = 100)
    private String emailId;

    @Column(name = "ENCRYPTEDPASSWORD", unique = false, nullable = false, length = 100)
    private String encryptedPassword;

}
