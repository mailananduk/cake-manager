package com.wracle.cakemgr.cakemanager.shared.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CakeDto implements Serializable {
    private static final long serialVersionUID = 3663464993322456907L;

    private Integer id;
    private String name;
    private String description;
    private String imageUrl;
}
