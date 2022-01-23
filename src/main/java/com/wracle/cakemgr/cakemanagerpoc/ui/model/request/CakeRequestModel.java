package com.wracle.cakemgr.cakemanagerpoc.ui.model.request;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Data
public class CakeRequestModel {
    private Integer id;
    private String name;
    private String description;
    private String imageUrl;
}
