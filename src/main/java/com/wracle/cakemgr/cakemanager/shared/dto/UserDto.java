package com.wracle.cakemgr.cakemanager.shared.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = 213954296949620762L;

    private Integer id;
    private String emailId;
    private String password;
    private String encryptedPassword;
}
