package com.wracle.cakemgr.cakemanager.ui.model.response;

import lombok.Data;

@Data
public class CakeRest {
    private Integer id;
    private String name;
    private String description;
    private String imageUrl;
}
